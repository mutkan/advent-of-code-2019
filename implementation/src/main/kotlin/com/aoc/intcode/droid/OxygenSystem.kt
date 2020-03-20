package com.aoc.intcode.droid

import math.Point2D

class OxygenSystem(private val map: ShipFloorMap) {

    /**
     * Fills the ship with oxygen.
     * The system is located on the [map] at the [Point2D] that is of type [ShipFloorTile.OXYGEN_SYSTEM].
     * Every minute the oxygen created by the system spreads to every adjacent tile until the entire
     * ship has been oxygenated.
     * @return The time taken (in minutes) to fully oxygenate the ship.
     */
    fun oxygenateShip(): Int {
        val tileToBeOxygenated = setOf(ShipFloorTile.TRAVERSABLE, ShipFloorTile.DROID, ShipFloorTile.OXYGEN_SYSTEM)
        val traversableTiles = map.data.filterValues { it in tileToBeOxygenated }.toMutableMap()
        val oxygenSystem = map.data.filterValues { it == ShipFloorTile.OXYGEN_SYSTEM }
        val nextTiles = mutableSetOf(oxygenSystem.keys.first())
        var minutesElapsed = 0
        while (map.data.containsValue(ShipFloorTile.TRAVERSABLE)) {
            val adjacentTraversable = nextTiles.flatMap { it.adjacentPoints() }.filter { it in traversableTiles.keys }
            nextTiles.clear()
            nextTiles.addAll(adjacentTraversable)
            traversableTiles.filterKeys { it in adjacentTraversable }.forEach {
                map.updateTile(it.key, ShipFloorTile.OXYGENATED)
            }
            //println(map)
            minutesElapsed++
        }
        return minutesElapsed
    }
}