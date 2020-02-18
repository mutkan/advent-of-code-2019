package com.aoc.intcode.amplifier

import com.aoc.intcode.IntCodeComputer

class FeedbackAmplifier(private val phaseSetting: Int) : BaseAmplifier(phaseSetting) {
    private var lastOutputSignal = 0

    override fun inputSignal(inputSignal: Int) {
        computer.getProgramMemory().systemInput(inputSignal)
        computer.compute()
        lastOutputSignal = computer.getDiagnosticCode()

        if (!computer.programHalted) nextAmplifier.inputSignal(lastOutputSignal)
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.getProgramMemory().systemInput(phaseSetting) //Set PhaseSetting exactly once
    }

    fun getThrusterSignal(): Int = lastOutputSignal
}