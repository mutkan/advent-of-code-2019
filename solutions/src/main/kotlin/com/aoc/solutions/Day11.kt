package com.aoc.solutions

import com.aoc.intcode.hull.HullPaint
import com.aoc.intcode.hull.SpaceshipController
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader.read<String>(Day.from(11)).asSingleString()
    val controller = SpaceshipController()
    val paintedHullStartingBlack = controller.deployEmergencyHullPaintingRobot(input, HullPaint.BLACK)
    println("Solution Part 1: ${paintedHullStartingBlack.getTimesPanelsPainted()}")

    val paintedHullStartingWhite = controller.deployEmergencyHullPaintingRobot(input, HullPaint.WHITE)
    println("Solution Part 2:\n${paintedHullStartingWhite}")
}