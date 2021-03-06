package com.aoc.panel

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PanelTest {
    private val wireStrings = InputReader.read<String>(Day(3)).value

    @Test
    @DisplayName("Given the wires from the basic example, when calculating the closest intersection point, then it should return 6")
    fun basicExample() {
        val panel = Panel(Wire("R8,U5,L5,D3"), Wire("U7,R6,D4,L4"))
        val distance = panel.findIntersectionPointClosestToCentralPort()
        assertThat(distance).isEqualTo(6)
    }

    @Test
    @DisplayName("Given the wires from example one, when calculating the closest intersection point, then it should return 159")
    fun exampleOne() {
        val firstWire = Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val secondWire = Wire("U62,R66,U55,R34,D71,R55,D58,R83")

        val panel = Panel(firstWire, secondWire)

        assertThat(panel.findIntersectionPointClosestToCentralPort()).isEqualTo(159)
    }

    @Test
    @DisplayName("Given the wires from example two, when calculating the closest intersection point, then it should return 135")
    fun exampleTwo() {
        val firstWire = Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val secondWire = Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        val panel = Panel(firstWire, secondWire)

        assertThat(panel.findIntersectionPointClosestToCentralPort()).isEqualTo(135)
    }

    @Test
    @DisplayName("Given Day 3 puzzle input, when calculating the manhattan distance, then it should return 529")
    internal fun solutionPartOne() {
        val panel = Panel(Wire(wireStrings[0]), Wire(wireStrings[1]))
        val distance = panel.findIntersectionPointClosestToCentralPort()
        assertThat(distance).isEqualTo(529)
    }

    @Test
    @DisplayName("Given Day 3 Part 2 example one puzzle input, when calculating the fewest combined steps to intersection, it should return 610")
    fun partTwoExampleOne() {
        val firstWire = Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val secondWire = Wire("U62,R66,U55,R34,D71,R55,D58,R83")

        val panel = Panel(firstWire, secondWire)

        assertThat(panel.findShortestCombinedIntersectionPaths()).isEqualTo(610)
    }

    @Test
    @DisplayName("Given Day 3 Part 2 example two puzzle input, when calculating the fewest combined steps to intersection, it should return 410")
    fun partTwoExampleTwo() {
        val firstWire = Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val secondWire = Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        val panel = Panel(firstWire, secondWire)

        assertThat(panel.findShortestCombinedIntersectionPaths()).isEqualTo(410)
    }

    @Test
    @DisplayName("Given Day 3 puzzle input, when calculating the shortest combined intersection distance, then it should return 20386")
    fun solutionPartTwo() {
        val panel = Panel(Wire(wireStrings[0]), Wire(wireStrings[1]))
        val distance = panel.findShortestCombinedIntersectionPaths()
        assertThat(distance).isEqualTo(20386)
    }

    @Test
    @DisplayName("Given two wires with no intersections, when finding the shortest combined intersection paths, then" +
    "it should throw an exception")
    fun noIntersectionsShouldThrowException() {
        val panel = Panel(Wire("D1"), Wire("U5"))
        val e = assertThrows<IllegalStateException> { panel.findShortestCombinedIntersectionPaths() }
        assertThat(e.message).isEqualTo("No Intersections Found")
    }
}