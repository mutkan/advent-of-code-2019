package com.aoc.intcode.computer.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.OpCode
import com.aoc.intcode.computer.instructions.strategies.JumpIfTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JumpIfTrueTest {
    private val strategy = JumpIfTrue()

    @Test
    @DisplayName("Given a Jump If True OpCode in POSITION mode, when the first parameter is non-zero, " +
    "then it should set the instruction pointer to the value at the index of the second parameter")
    fun jumpIfTrueFirstParameterNonZeroPositionMode() {
        val opCode = OpCode("5")
        val memorySnapshot = Memory(listOf(5, 3, 1, 99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a Jump If True OpCode in POSITION mode, when the first parameter is zero," +
    "then it should increment the instruction pointer by the instruction length")
    fun jumpIfTrueFirstParameterZeroPositionMode() {
        val opCode = OpCode("5")
        val memorySnapshot = Memory(listOf(5, 2, 0, 99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a Jump If True OpCode in IMMEDIATE mode, when the first parameter is non-zero, " +
    "then it should set the instruction pointer to the value of the second parameter")
    fun jumpIfTrueFirstParameterNonZeroImmediateMode() {
        val opCode = OpCode("1105")
        val memorySnapshot = Memory(listOf(1105, 3, 1, 99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(1)
    }

    @Test
    @DisplayName("Given a Jump If True OpCode in IMMEDIATE mode, when the first parameter is zero," +
    "then it should increment the instruction pointer by it's instruction length")
    fun jumpIfTrueFirstParameterZeroImmediateMode() {
        val opCode = OpCode("1105")
        val memorySnapshot = Memory(listOf(1105, 0, 1, 99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a JIT OpCode (2205) in RELATIVE mode, when the value at the index given by the sum of the" +
    "relative base and the first parameter is non-zero, then it should set the instruction pointer to the value" +
    "given by the sum of the relative base and the second parameter")
    fun jumpIfTrueFirstParameterNonZeroRelativeMode() {
        val opCode = OpCode("2205")
        val memorySnapshot = Memory(listOf(2205, 4, 4, 99, 87))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(87)
    }

    @Test
    @DisplayName("Given a JIT OpCode (2205) in RELATIVE mode, when the value at the index given by the sum of the" +
    "relative base and the first parameter is zero, then it should set the instruction pointer to the instruction length")
    fun jumpIfTrueFirstParameterZeroRelativeMode() {
        val opCode = OpCode("2205")
        val memorySnapshot = Memory(listOf(2205, 4, 4, 99, 0))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }
}