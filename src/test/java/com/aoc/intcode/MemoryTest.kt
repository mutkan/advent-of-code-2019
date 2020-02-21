package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class MemoryTest {
    private val initialMemorySnapshot = listOf<Long>(1, 2, 3, 4, 5)

    @Test
    @DisplayName("Given a valid address input, when creating memory, it should return the addresses")
    fun createMemory() {
        val memory = Memory(initialMemorySnapshot)
        assertThat(memory.instructions).isEqualTo(listOf<Long>(1,2,3,4,5))
    }

    @Test
    @DisplayName("Given valid memory, when updating an address and then resetting, it should set the addresses back to their origin snapshot")
    fun reset() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateInstructionAtAddress(0, 10)
        memory.reset()
        assertThat(memory.instructions).isEqualTo(listOf<Long>(1,2,3,4,5))
    }

    @Test
    @DisplayName("Given a valid address index, when updating the value at that address, then it should set the new value correctly")
    fun updateAddress() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateInstructionAtAddress(0, 12)
        assertThat(memory.getInstructionAtAddress(0)).isEqualTo(12)
    }

    @Test
    @DisplayName("Given a valid set of addresses, when getting an address via index, it should return the correct addresss")
    fun getAddress() {
        val memory = Memory(initialMemorySnapshot)
        val addressValue = memory.getInstructionAtAddress(3)
        assertThat(addressValue).isEqualTo(4)
    }

    @Test
    @DisplayName("Given a valid set of addresses, when getting the instruction at a positive address that does not exist," +
    "then it should return 0")
    fun getFromNonExistentPositiveAddress() {
        val memory = Memory(initialMemorySnapshot)
        val addressValue = memory.getInstructionAtAddress(50)
        assertThat(addressValue).isEqualTo(0)
    }

    @Test
    @DisplayName("Given a valid set of addresses, when getting the instruction at a negative address (that cannot exist)," +
    "then it should throw an IllegalArgumentException")
    fun getFromNonExistentNegativeAddress() {
        val memory = Memory(initialMemorySnapshot)
        val e = assertThrows<IllegalArgumentException> { memory.getInstructionAtAddress(-1) }
        assertThat(e.message).isEqualTo("Invalid Memory Address (-1)")
    }

    @Test
    @DisplayName("Given a valid set of addresses, when updating the instruction value at a positive address that does" +
    "not exist, then it should pad the intermediary addresses with 0s and write the value")
    fun writeToNonExistentPositiveAddress() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateInstructionAtAddress(8, 4986L)
        assertThat(memory.getInstructionAtAddress(6)).isEqualTo(0)
        assertThat(memory.getInstructionAtAddress(7)).isEqualTo(0)
        assertThat(memory.getInstructionAtAddress(8)).isEqualTo(4986L)
    }

    @Test
    @DisplayName("Given a valid set of addresses, when updating the instruction value at a negative address" +
            "(that cannot exist), then it should throw an IllegalArgumentException")
    fun writeToNonExistentNegativeAddress() {
        val memory = Memory(initialMemorySnapshot)
        val e = assertThrows<IllegalArgumentException> { memory.updateInstructionAtAddress(-125, 0L) }
        assertThat(e.message).isEqualTo("Invalid Memory Address (-125)")
    }

    @Test
    @DisplayName("Given a memory with a single system input, when getting the input, then it should consume and return the value")
    fun getInputShouldConsumeValue() {
        val memory = Memory(initialMemorySnapshot)
        memory.systemInput(1)
        val input = memory.getInput()
        assertThat(input).isEqualTo(1)
        assertThat(memory.input).isEmpty()
    }

    @Test
    @DisplayName("Given a memory with no system input, when getting the input, then it should return null")
    fun getInputWhenEmpty() {
        val memory = Memory(initialMemorySnapshot)
        val input = memory.getInput()
        assertThat(input).isNull()
    }
}