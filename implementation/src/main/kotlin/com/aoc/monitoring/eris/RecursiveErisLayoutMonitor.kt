package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger

class RecursiveErisLayoutMonitor(private val layout: RecursiveErisPlanetLayout) {

    init {
        AdventLogger.info("Initial State:")
        AdventLogger.info(layout)
    }

    fun watch(minutes: Int): Int {
        repeat(minutes) {
            incrementTime(it)
        }
        return layout.getBugQuantity()
    }

    private fun incrementTime(minute: Int) {
        val dyingBugs = layout.getDyingBugs()
        val infestedTiles = layout.getInfestedTiles()
        layout.kill(dyingBugs)
        layout.infest(infestedTiles)
        AdventLogger.info("After ${minute + 1} Minute(s):")
        AdventLogger.info(layout)
    }
}