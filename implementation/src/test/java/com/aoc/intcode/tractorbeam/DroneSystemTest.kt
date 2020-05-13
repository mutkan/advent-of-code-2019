package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DroneSystemTest {
    @Test
    fun partOneSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val scan = DroneSystem(input).scanAreaSurroundingEmitter(50)
        assertThat(scan.getPointsAffectedByBeam()).isEqualTo(181)
    }

    @Test
    fun scanAreaGridSizeShouldCreateBeamScanWithEqualWidthAndHeight() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val scan = DroneSystem(input).scanAreaSurroundingEmitter(50)
        assertThat(scan.getArea()).isEqualTo(2500)
    }

    @Test
    fun bigScan() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val scan = DroneSystem(input).scanAreaSurroundingEmitter(150)
    }

    @Test
    fun partTwo() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val scan = DroneSystem(input).scanAreaForSantasShip()

    }
}