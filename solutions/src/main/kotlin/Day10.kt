package com.aoc.solutions

import input.Day
import input.InputReader
import com.aoc.monitoring.asteroid.AsteroidMap

fun main() {
    val input = InputReader().readInputString(Day.from(10)).values

    val asteroidMap = AsteroidMap(input)
    print("Solution Part 1: ${asteroidMap.getOptimalAsteroidMappingStationSector()}")
    print("Solution Part 2: ${asteroidMap.winBetWithElves()}")
}