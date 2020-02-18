package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.message
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.exceptions.SignalInterrupt
import com.aoc.intcode.instructions.strategies.Input
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputTest {
    private val strategy = Input()

    @Test
    @DisplayName("Given a single digit Input OpCode (3), when executing the strategy, then it should take a value from " +
    "the system input and store it in the address indexed by the first parameter value")
    fun inputPositionMode() {
        val opCode = OpCode("3")
        val memorySnapshot = Memory(listOf(1,0,15,12,3,1,99))
        memorySnapshot.incrementInstructionPointer(4)
        memorySnapshot.systemInput(12)

        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)

        assertThat(finalSnapshot).isEqualTo(Memory(listOf(1,12,15,12,3,1,99)))
    }

    @Test
    @DisplayName("Given an Input OpCode (3) and NO system input, when executing the strategy, then it should throw a " +
    "signal interrupt to inform the computer to wait")
    fun inputWhenSystemMemoryHasNoInput() {
        val opCode = OpCode("3")
        val memorySnapshot = Memory(listOf(3,0,99))
        val e = assertThrows<SignalInterrupt> { strategy.execute(memorySnapshot, opCode.parameterModes) }
        assertThat(e.message).isEqualTo("SIGINT")
    }

    @Test
    @Disabled("Input Opcode cannot be in immediate mode and the first and only parameter is a write to")
    @DisplayName("Given an Input OpCode in IMMEDIATE MODE (1103), when executing the strategy, then it should take a value from the system input")
    fun inputImmediateMode() {
        val opCode = OpCode("1103")
        val memorySnapshot = Memory(listOf(4,9,3,1,99))
        memorySnapshot.incrementInstructionPointer(2)
        memorySnapshot.systemInput(3)

        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)

        assertThat(finalSnapshot).isEqualTo(Memory(listOf(4,3,3,1,99)))
    }
}