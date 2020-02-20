package com.aoc.intcode.instructions.strategies

import com.aoc.intcode.instructions.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.instructions.InstructionStrategy
import java.util.*

class Multiply : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstValue = getValue(memorySnapshot, modes.pop(), 1)
        val secondValue = getValue(memorySnapshot, modes.pop(), 2)
        val updateAddress = getWriteToIndex(memorySnapshot, 3)
        memorySnapshot.updateInstructionAtAddress(updateAddress, firstValue * secondValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.FOUR_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }
}