package com.aoc.intcode

import com.aoc.intcode.InstructionLength.*
import com.aoc.intcode.strategy.*
import java.util.*

data class OpCode(val instructionValue: String) {
    private var value: Int = 0
    private val parameterModes: Stack<ParameterMode> = Stack()

    init {
        val paddedValue = instructionValue.padStart(4, '0')
        if (paddedValue.takeLast(2).toInt() == 99) {
            this.value = 99
        } else {
            this.value = instructionValue.takeLast(1).toInt()
        }
        paddedValue.take(paddedValue.length - 2).forEach {
            if (it == '1') {
                parameterModes.push(ParameterMode.IMMEDIATE)
            } else {
                parameterModes.push(ParameterMode.POSITION)
            }
        }
    }

    fun operation(): InstructionType {
        return when(value) {
            1 -> InstructionType.ADD
            2 -> InstructionType.MULTIPLY
            3 -> InstructionType.INPUT
            4 -> InstructionType.OUTPUT
            5 -> InstructionType.JUMP_IF_TRUE
            6 -> InstructionType.JUMP_IF_FALSE
            7 -> InstructionType.LESS_THAN
            8 -> InstructionType.EQUALS
            99 -> InstructionType.HALT
            else -> InstructionType.UNKNOWN
        }
    }

    fun getOperationStrategy(): InstructionStrategy {
        return when(value) {
            1 -> Add()
            2 -> Multiply()
            3 -> Input()
            4 -> Output()
            5 -> TODO()
            6 -> TODO()
            7 -> TODO()
            8 -> TODO()
            99 -> TODO()
            else -> TODO()
        }
    }

    fun instructionLength(): Int {
        return when(value) {
            1 -> FOUR_ADDRESS_INSTRUCTION.length
            2 -> FOUR_ADDRESS_INSTRUCTION.length
            3 -> TWO_ADDRESS_INSTRUCTION.length
            4 -> TWO_ADDRESS_INSTRUCTION.length
            5 -> THREE_ADDRESS_INSTRUCTION.length
            6 -> THREE_ADDRESS_INSTRUCTION.length
            7 -> FOUR_ADDRESS_INSTRUCTION.length
            8 -> FOUR_ADDRESS_INSTRUCTION.length
            99 -> ONE_ADDRESS_INSTRUCTION.length
            else -> throw IllegalArgumentException("Invalid OpCode Value: $instructionValue")
        }
    }

    fun getParameterMode(): ParameterMode {
        return parameterModes.pop()
    }

    fun isValid() = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 99).contains(value)

    fun getValue() = instructionValue

}