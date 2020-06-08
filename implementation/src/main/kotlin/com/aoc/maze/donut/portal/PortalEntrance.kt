package com.aoc.maze.donut.portal

import com.aoc.math.Point2D
import com.aoc.maze.donut.PlutonianMaze
import kotlin.math.abs

/**
 * The entrance to a [Portal] in a [PlutonianMaze].
 *
 * @param warpCode The two tiles in the [PlutonianMaze] that represent the portals name. E.g 'FG'
 * @param position The tile which upon stepping on, warps you to the other portal entrance.
 */
class PortalEntrance(val warpCode: WarpCode, val position: Point2D, private val xMax: Int, private val yMax: Int) {

    /**
     * @return True if the portal is on the outside of the [PlutonianMaze], false if is on in the inside.
     */
    fun isOuter(): Boolean {
        return position.distanceFromAxisY() == 2
                || position.distanceFromAxisX() == 2
                || abs(xMax - position.x) == 2
                || abs(yMax - position.y) == 2
    }

    fun isInner() = !isOuter()

    override fun toString() = position.toString()
}