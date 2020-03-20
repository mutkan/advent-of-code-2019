package com.aoc.intcode.droid

import com.aoc.intcode.computer.IntCodeComputer
import math.Point2D
import java.util.*

class RepairDroid(instructions: String) {
    private val computer = IntCodeComputer(instructions)
    private val path = Stack<Pair<Point2D, Direction>>()
    private val map = ShipFloorMap()
    private var x = 0
    private var y = 0

    /**
     * @return The current [ShipFloorMap] that the [RepairDroid] has mapped during its exploration.
     */
    fun downloadShipMappingData() = map

    /**
     * Command the [RepairDroid] to explore the ship in order to find the Oxygen System.
     * The search algorithm is a DFS (Depth First Search), meaning it is not the most efficient.
     * @return The coordinates of the destination and the number of movements the droid has to take in order to reach it.
     */
    fun locateShipsOxygenSystem(): Pair<Point2D, Int> {
        //Set Starting Tile & Initial Movement Direction
        path.push(Pair(Point2D(0, 0), Direction.NORTH))

        var oxygenSystemInfo: Pair<Point2D, Int>? = null

        while (!path.isEmpty()) {
            val direction = getNextDirection()

            computer.getProgramMemory().input.add(direction.code.toLong())
            computer.compute()

            when (DroidResponse.fromCode(computer.getDiagnosticCode().toInt())) {
                DroidResponse.HIT_WALL_POSITION_NOT_CHANGED -> {
                    //Record Wall Coordinate
                    val wall = ShipFloorTile.WALL
                    when (direction) {
                        Direction.NORTH -> map.updateTile(Point2D(x, y + 1), wall)
                        Direction.EAST -> map.updateTile(Point2D(x + 1, y), wall)
                        Direction.SOUTH -> map.updateTile(Point2D(x, y - 1), wall)
                        Direction.WEST -> map.updateTile(Point2D(x - 1, y), wall)
                    }
                }
                DroidResponse.SUCCESSFULLY_CHANGED_POSITION -> {
                    //Record Current Coordinate As Traversable (Unless Inside Oxygen System)
                    if (map.tileType(Point2D(x, y)) != ShipFloorTile.OXYGEN_SYSTEM) {
                        map.updateTile(Point2D(x, y), ShipFloorTile.TRAVERSABLE)
                    }

                    //Increment Relevant Ordinate
                    move(direction)

                    //Updated Visited Tiles (Unless Backtracking)
                    if (!map.hasRecorded(Point2D(x, y))) {
                        path.push(Pair(Point2D(x, y), direction))
                    }

                    //Record New Coordinate As Droid Current Position
                    map.updateTile(Point2D(x, y), ShipFloorTile.DROID)
                }
                DroidResponse.LOCATED_OXYGEN_SYSTEM -> {
                    //Record Current Coordinate As Traversable
                    map.updateTile(Point2D(x, y), ShipFloorTile.TRAVERSABLE)

                    //Increment Relevant Ordinate
                    move(direction)

                    //Log Oxygen System Details
                    oxygenSystemInfo = Pair(Point2D(x, y), path.size)
                    map.updateTile(Point2D(x, y), ShipFloorTile.OXYGEN_SYSTEM)

                    //Log Oxygen System Visit
                    path.push(Pair(Point2D(x, y), direction))
                }
            }
        }

        println(map)
        println("Oxygen System Location ${oxygenSystemInfo!!.first}")
        return oxygenSystemInfo
    }

    private fun move(direction: Direction) {
        when (direction) {
            Direction.NORTH -> y++
            Direction.EAST -> x++
            Direction.SOUTH -> y--
            Direction.WEST -> x--
        }
    }

    private fun getNextDirection() = getUnexploredSurroundingCoordinateDirection() ?: path.pop().second.reverse()

    private fun getUnexploredSurroundingCoordinateDirection(): Direction? {
        if (!map.hasRecorded(Point2D(x, y + 1))) return Direction.NORTH
        if (!map.hasRecorded(Point2D(x + 1, y))) return Direction.EAST
        if (!map.hasRecorded(Point2D(x, y - 1))) return Direction.SOUTH
        if (!map.hasRecorded(Point2D(x - 1, y))) return Direction.WEST
        return null
    }
}