package com.aoc.intcode.amplifier.loopback

import com.aoc.intcode.IntCodeComputer
import com.aoc.intcode.amplifier.BaseAmplifier

class FeedbackAmplifier(private val phaseSetting: Long) : BaseAmplifier(phaseSetting) {
    private var lastOutputSignal = 0L

    override fun inputSignal(inputSignal: Long) {
        computer.getProgramMemory().systemInput(inputSignal)
        computer.compute()
        lastOutputSignal = computer.getDiagnosticCode()

        if (!computer.programHalted) nextAmplifier.inputSignal(lastOutputSignal)
    }

    override fun loadAmplifierControllerSoftware(software: String) {
        computer = IntCodeComputer(software)
        computer.getProgramMemory().systemInput(phaseSetting)
    }

    fun getThrusterSignal(): Long = lastOutputSignal
}