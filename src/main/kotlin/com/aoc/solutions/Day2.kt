package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.IntCodeComputer

fun main() {
    val inputReader = InputReader()
    val memoryAddresses = inputReader.readInputAsSingleString(Day.from(2))

    partOne(memoryAddresses)
    partTwo(memoryAddresses)
}

private fun partOne(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.restoreGravityAssistProgram(12, 2)
    computer.compute()
    println("Solution 1: " + computer.getProgramMemory().getInstructionAtAddress(0))
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)

    for (noun in 0..99) {
        for (verb in 0..99) {
            computer.restoreGravityAssistProgram(noun, verb)
            computer.compute()
            if (computer.getProgramMemory().getInstructionAtAddress(0) == 19690720) println("Solution 2: " + (100 * noun + verb))
            computer.getProgramMemory().reset()
        }
    }
}