package com.aoc.fuel.factory

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.fuel.factory.components.Chemical
import com.aoc.fuel.factory.components.Ore
import org.junit.jupiter.api.Test

class ReactionTest {
    @Test
    fun toStringTest() {
        assertThat(Reaction(setOf(Ore(10)), Chemical("A", 10)).toString()).isEqualTo("10 ORE => 10 A")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(Reaction(setOf(Ore(10)), Chemical("KLA", 17))).isEqualTo(Reaction(setOf(Ore(10)), Chemical("KLA", 17)))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(Reaction(setOf(Ore(11)), Chemical("BHA", 5))).isNotEqualTo(Reaction(setOf(Ore(10)), Chemical("KLA", 17)))
    }
}