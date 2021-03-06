package com.aoc.solutions

import com.aoc.Day
import com.aoc.cards.ShuffleInstructionParser
import com.aoc.cards.SpaceCardDeckFactory
import com.aoc.cards.SpaceCardDeckShuffler
import com.aoc.input.InputReader

fun main() {
    val input = InputReader.read<String>(Day(22)).value
    val instructions = ShuffleInstructionParser.parse(input)
    val deck = SpaceCardDeckFactory.default()
    val shuffler = SpaceCardDeckShuffler(instructions)
    println("Part 1 Solution: ${shuffler.shuffle(deck).getCardWithValue(2019)}")

    val giantDeck = SpaceCardDeckFactory.factoryOrder(119315717514047)
    println("Part 2 Solution: ${shuffler.shuffle(giantDeck, 101741582076661).getCard(2020).value}")
}