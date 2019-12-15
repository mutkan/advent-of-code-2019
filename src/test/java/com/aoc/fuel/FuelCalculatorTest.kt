package com.aoc.fuel

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.common.InputReader
import com.aoc.value.Day
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


internal class FuelCalculatorTest {
    private val calculator: FuelCalculator = FuelCalculator()
    private val puzzleInput = InputReader().readInputInteger(Day.from(1))

    @ParameterizedTest
    @ValueSource(ints = [12, 14])
    @DisplayName("Given a mass of 12 or 14, when calculating the mass for the module, then it should return 2")
    internal fun examplesOneAndTwo(mass: Int) {
        val fuel = calculator.calculateModuleFuel(mass)
        assertThat(fuel).isEqualTo(2)
    }

    @Test
    @DisplayName("Given a mass of 1969, when calculating the mass for the module, then it should return 654")
    internal fun exampleThree() {
        val fuel = calculator.calculateModuleFuel(1969)
        assertThat(fuel).isEqualTo(654)
    }

    @Test
    @DisplayName("Given a mass of 100756, when calculating the mass for the module, then it should return 33583")
    internal fun exampleFour() {
        val fuel = calculator.calculateModuleFuel(100756)
        assertThat(fuel).isEqualTo(33583)
    }

    @Test
    @DisplayName("Given Day 1 - Part 1 puzzle input, when calculating the total fuel for the module, then it should return 3184233")
    internal fun partOneSolution() {
        val solution = calculator.calculateTotalFuelForModule(puzzleInput.values)
        assertThat(solution).isEqualTo(3184233)
    }

    @Test
    @DisplayName("Given a module of mass 14, when calculating additional fuel, then it should only return 2")
    internal fun additionalFuelExampleOne() {
        val additionalFuel = calculator.calculateAdditionalFuel(14);
        assertThat(additionalFuel).isEqualTo(2)
    }

    @Test
    @DisplayName("Given a module of mass 1969, when calculating additional fuel, then it should only return 966")
    internal fun additionalFuelExampleTwo() {
        val additionalFuel = calculator.calculateAdditionalFuel(1969);
        assertThat(additionalFuel).isEqualTo(966)
    }

    @Test
    @DisplayName("Given a module of mass 100756, when calculating additional fuel, then it should only return 50346")
    internal fun additionalFuelExampleThree() {
        val additionalFuel = calculator.calculateAdditionalFuel(100756);
        assertThat(additionalFuel).isEqualTo(50346)
    }

    @Test
    @DisplayName("Given Day 1 - Part 1 puzzle input, when calculating to the total additional fuel required, then it should return 4773483")
    internal fun partTwoSolution() {
        val solution = calculator.calculateTotalAdditionFuelForModule(puzzleInput.values)
        assertThat(solution).isEqualTo(4773483)
    }
}