package com.aoc.solutions

import input.Day
import input.InputReader
import com.aoc.monitoring.asteroid.AsteroidMap

fun main() {
    val input = InputReader().readInputString(Day.from(10)).values

    val asteroidMap = AsteroidMap(input)
    println("Solution Part 1: ${asteroidMap.getOptimalAsteroidMappingStationSector()}")
    println("Solution Part 2: ${asteroidMap.winBetWithElves()}")
}