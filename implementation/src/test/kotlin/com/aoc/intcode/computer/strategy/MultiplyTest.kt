package com.aoc.intcode.computer.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.OpCode
import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.instructions.strategies.Multiply
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MultiplyTest {
    private val strategy = Multiply()

    @Test
    @DisplayName("Given a single digit Multiply OpCode (3), when executing the strategy, then it should store the " +
            "product of the first and second parameters in the address index of the third parameter value")
    fun multiplySingleDigitPositionMode() {
        val opCode = OpCode("3")
        val memorySnapshot = Memory(listOf(3, 0, 0, 0))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(9, 0, 0, 0)))
    }

    @Test
    @DisplayName("Given a valid memory snapshot with a non-zero instruction pointer, when executing multiply, then it should update the correct addresses in memory")
    fun multiplyWithNonZeroInstructionPointer() {
        val opCode = OpCode("3")
        val memorySnapshot = Memory(listOf(3, 4, 99, 10, 3, 3, 3))
        memorySnapshot.incrementInstructionPointer(InstructionLength.THREE_ADDRESS_INSTRUCTION)
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(3, 4, 99, 100, 3, 3, 3)))
    }
}