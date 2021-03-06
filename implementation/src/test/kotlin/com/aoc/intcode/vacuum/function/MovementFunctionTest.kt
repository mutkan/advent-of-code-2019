package com.aoc.intcode.vacuum.function

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.vacuum.function.FunctionParameter.LEFT
import com.aoc.intcode.vacuum.function.FunctionParameter.RIGHT
import org.junit.jupiter.api.Test

class MovementFunctionTest {
    @Test
    fun exampleFunctionA() {
        val movementFunction = MovementFunctionA().add(RIGHT, 8).add(RIGHT, 8)
        val sequence = movementFunction.getSequence()
        assertThat(sequence).isEqualTo(listOf(82,44,56,44,82,44,56,10))
    }

    @Test
    fun exampleFunctionB() {
        val movementFunction = MovementFunctionB().add(RIGHT, 4).add(RIGHT, 4).add(RIGHT, 8)
        val sequence = movementFunction.getSequence()
        assertThat(sequence).isEqualTo(listOf(82,44,52,44,82,44,52,44,82,44,56,10))
    }

    @Test
    fun exampleFunctionC() {
        val movementFunction = MovementFunctionC().add(LEFT, 6).add(LEFT, 2)
        val sequence = movementFunction.getSequence()
        assertThat(sequence).isEqualTo(listOf(76,44,54,44,76,44,50,10))
    }

    @Test
    fun functionHasUnitValueGreaterThan9() {
        assertThat(MovementFunctionA().add(LEFT, 12).getSequence()).isEqualTo(listOf(76,44,49,50,10))
    }
}