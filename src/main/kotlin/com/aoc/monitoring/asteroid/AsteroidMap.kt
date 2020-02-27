package com.aoc.monitoring.asteroid

class AsteroidMap(mapData: List<String>) {
    private var map: Set<MapSector>
    private var asteroids: Set<MapSector>

    init {
        map = mapData.mapIndexed { y, row ->
            row.chunked(1).mapIndexed { x, contents -> MapSector(contents, x, y) }
        }.flatten().toSet()

        asteroids = map.filter { it.hasAsteroid() }.toSet()
    }

    fun getRow(index: Int) = map.filter { it.y == index }.joinToString(separator = "") { it.contents }

    /**
     * @return A [Pair] of the optimal [MapSector] and the number of asteroids within
     * line of sight of the monitoring station if it was placed in this sector.
     */
    fun getOptimalAsteroidMappingStationSector(): Pair<MapSector, Int> {
        val angleMap: MutableMap<MapSector, Set<Double>> = mutableMapOf()
        asteroids.map { sourceAsteroid ->
            val targetAsteroids = asteroids.filter { it != sourceAsteroid }
            angleMap.put(sourceAsteroid, targetAsteroids.map { targetAsteroid ->
                sourceAsteroid.angleBetween(targetAsteroid)
            }.toSet())
        }

        val optimal = angleMap.maxBy { it.value.size }

        return Pair(optimal!!.key, optimal.value.size)
    }

    /**
     * The Instant Monitoring Station that was deployed on the asteroid of the optimal [MapSector] fires a
     * vaporising laser that starts from the vertical position and rotates clockwise until all the asteroids on
     * the map have been destroyed.
     * @return The [MapSector] containing the [n]th asteroid to be destroyed.
     */
    fun vaporiseAsteroidBelt(n: Int): MapSector {
        val laserStation = getOptimalAsteroidMappingStationSector().first
        val angleSectorMap: MutableMap<Double, MutableList<MapSector>> = asteroids
                .filter { it != laserStation }
                .groupBy { laserStation.angleBetween(it) }
                .mapValues { entry -> entry.value.sortedBy { laserStation.distanceBetween(it) }.toMutableList() }
                .toSortedMap()

        val vaporised = mutableListOf<MapSector>()
        while (angleSectorMap.isNotEmpty()) {
            angleSectorMap.forEach { vaporised.add(it.value.removeAt(0)) }
            angleSectorMap.filterKeys { angleSectorMap.getValue(it).isEmpty() }.forEach { angleSectorMap.remove(it.key) }
        }

        return vaporised[n - 1]
    }

    /**
     * To win the bet with the elves on which asteroid be the 200th to be vaporised, this function returns the value
     * when multiplying the asteroids x-ordinate by 100 and adding it it's y-ordinate.
     * @return ([MapSector.x] * 100) + [MapSector.y].y
     */
    fun winBetWithElves(): Int {
        val asteroid = vaporiseAsteroidBelt(200)
        return (asteroid.x * 100) + asteroid.y
    }

}