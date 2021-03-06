package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.monitoring.eris.PlanarErisLayoutMonitor
import com.aoc.monitoring.eris.PlanarErisPlanetLayout
import com.aoc.monitoring.eris.RecursiveErisLayoutMonitor
import com.aoc.monitoring.eris.RecursiveErisPlanetLayout

fun main() {
    val scanData = InputReader.read<String>(Day(24)).value

    val planarMonitor = PlanarErisLayoutMonitor(PlanarErisPlanetLayout(scanData))
    println("Solution Part 1: ${planarMonitor.watchForRepeatingLayout().getBioDiversityRating()}")

    val recursiveMonitor = RecursiveErisLayoutMonitor(RecursiveErisPlanetLayout(scanData))
    println("Solution Part 2: ${recursiveMonitor.watch(200)} bugs")
}