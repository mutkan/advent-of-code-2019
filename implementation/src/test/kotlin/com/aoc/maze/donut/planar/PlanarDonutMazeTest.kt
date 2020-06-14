package com.aoc.maze.donut.planar

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class PlanarDonutMazeTest {
    @Test
    fun parsePuzzleInputData() {
        val input = InputReader().readInputString(Day.from(20))
        PlanarDonutMaze(input.values)
    }

    @Test
    fun exampleMazeShouldHaveThreePortalPairs() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.values)
        assertThat(maze.portals.size).isEqualTo(3)
    }

    @Test
    fun exampleMazeShouldParseEntranceCorrectly() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.values)
        assertThat(maze.entrance).isEqualTo(Point2D(9,2))
    }

    @Test
    fun exampleMazeShouldParseExitCorrectly() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.values)
        assertThat(maze.exit).isEqualTo(Point2D(13,16))
    }

    @Test
    fun findShortestPathExampleMaze() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.values)
        assertThat(maze.findShortestPath()).isEqualTo(23)
    }

    @Test
    fun findShortestPathLargeExampleMaze() {
        val input = InputReader().readInputAsString("/maze/donut/large-example-maze.txt")
        val maze = PlanarDonutMaze(input.values)
        assertThat(maze.findShortestPath()).isEqualTo(58)
    }

    @Test
    fun solutionPartOne() {
        val input = InputReader().readInputString(Day.from(20))
        val maze = PlanarDonutMaze(input.values)
        assertThat(maze.findShortestPath()).isEqualTo(526)
    }
}