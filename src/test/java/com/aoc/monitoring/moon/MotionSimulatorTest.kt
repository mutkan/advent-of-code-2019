package com.aoc.monitoring.moon

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MotionSimulatorTest {
    @Test
    @DisplayName("Given Day 12 Example 1 Input Data, when calculating the systems total energy after 10 time steps, then it should return 179")
    fun totalEnergyExampleOne() {
        val exampleInput = InputReader().readInputAsString("/moon/example-moon-positions-1.txt").values
        val moonPositions = ScanningModule().scanLocalSectorForMoons(exampleInput)
        val motionSimulator = MotionSimulator(moonPositions)
        motionSimulator.simulate(10)
        val totalSystemEnergy = motionSimulator.calculateTotalSystemEnergy()
        assertThat(totalSystemEnergy).isEqualTo(179)
    }

    @Test
    @DisplayName("Given Day 12 Example 2 Input Data, when calculating the systems total energy after 100 time steps, then it should return 1940")
    fun totalEnergyExampleTwo() {
        val exampleInput = InputReader().readInputAsString("/moon/example-moon-positions-2.txt").values
        val moonPositions = ScanningModule().scanLocalSectorForMoons(exampleInput)
        val motionSimulator = MotionSimulator(moonPositions)
        motionSimulator.simulate(100)
        val totalSystemEnergy = motionSimulator.calculateTotalSystemEnergy()
        println("\nEnergy after 100 steps:")
        moonPositions.forEach { println("pot: ${it.calculatePotentialEnergy()};   kin: ${it.calculateKineticEnergy()};   total: ${it.calculateTotalEnergy()}") }
        assertThat(totalSystemEnergy).isEqualTo(1940)
    }

    @Test
    @DisplayName("Given Day 12 Example 1 Input Data, when determining the number of time steps until history repeats" +
    "itself, then it should return 2772 time steps.")
    fun determineWhenHistoryRepeatsItselfExampleOne() {
        val exampleInput = InputReader().readInputAsString("/moon/example-moon-positions-1.txt").values
        val moonPositions = ScanningModule().scanLocalSectorForMoons(exampleInput)
        val motionSimulator = MotionSimulator(moonPositions)
        val timeSteps = motionSimulator.determineTimeStepsUntilHistoryRepeats()
        assertThat(timeSteps).isEqualTo(2772)
    }

    @Test
    @DisplayName("Given Day 12 Example 2 Input Data, when determining the number of time steps until history repeats" +
    "itself, then it should return 4686774924 time steps.")
    fun determineWhenHistoryRepeatsItselfExampleTwo() {
        val exampleInput = InputReader().readInputAsString("/moon/example-moon-positions-2.txt").values
        val moonPositions = ScanningModule().scanLocalSectorForMoons(exampleInput)
        val motionSimulator = MotionSimulator(moonPositions)
        val timeSteps = motionSimulator.determineTimeStepsUntilHistoryRepeats()
        assertThat(timeSteps).isEqualTo(4686774924)
    }

    @Test
    fun partOneSolution() {
        val input = InputReader().readInputString(Day.from(12)).values
        val motionSimulator = MotionSimulator(ScanningModule().scanLocalSectorForMoons(input))
        motionSimulator.simulate(1000)
        assertThat(motionSimulator.calculateTotalSystemEnergy()).isEqualTo(7722)
    }

    @Test
    fun partTwoSolution() {
        val input = InputReader().readInputString(Day.from(12)).values
        val motionSimulator = MotionSimulator(ScanningModule().scanLocalSectorForMoons(input))
        assertThat(motionSimulator.determineTimeStepsUntilHistoryRepeats()).isEqualTo(292653556339368L)
    }
}