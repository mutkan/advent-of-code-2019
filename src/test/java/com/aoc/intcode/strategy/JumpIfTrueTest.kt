package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JumpIfTrueTest {
    private val strategy = JumpIfTrue()

    @Test
    @DisplayName("Given a Jump If True OpCode in POSITION mode, when the first parameter is non-zero, " +
    "then it should set the instruction pointer to the value at the index of the second parameter")
    fun jumpIfTrueFirstParameterNonZeroPositionMode() {
        val memorySnapshot = Memory(listOf(5,3,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a Jump If True OpCode in POSITION mode, when the first parameter is zero," +
    "then it should increment the instruction pointer by the instruction length")
    fun jumpIfTrueFirstParameterZeroPositionMode() {
        val memorySnapshot = Memory(listOf(5,2,0,99))
        val finalSnapshot = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(2)
    }

    @Test
    @DisplayName("Given a Jump If True OpCode in IMMEDIATE mode, when the first parameter is non-zero, " +
    "then it should set the instruction pointer to the value of the second parameter")
    fun jumpIfTrueFirstParameterNonZeroImmediateMode() {
        val memorySnapshot = Memory(listOf(5,3,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, ParameterMode.IMMEDIATE)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(1)
    }

    @Test
    @DisplayName("Given a Jump If True OpCode in IMMEDIATE mode, when the first parameter is zero," +
    "then it should increment the instruction pointer by it's instruction length")
    fun jumpIfTrueFirstParameterZeroImmediateMode() {
        val memorySnapshot = Memory(listOf(5,0,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, ParameterMode.IMMEDIATE)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(2)
    }
}