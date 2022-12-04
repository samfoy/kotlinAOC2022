package io.github.samfoy.aoc2022.day2

import io.github.samfoy.aoc2022.asLines
import io.github.samfoy.aoc2022.getInputForDay

suspend fun main() {
    fun part1(input: List<String>) = input
        .map { it.split(" ") }
        .sumOf { gameScore(it) + shapeScore(it.last()) }

    fun part2(input: List<String>) = input
        .map { replace(it.split(" ")) }
        .sumOf { gameScore(it) + shapeScore(it.last()) }

    val input = getInputForDay(2).asLines()
    println(part1(input))
    println(part2(input))
}

fun replace(input: List<String>): List<String?> {
    val first = input.first()

    return when (input.last()) {
        "X" -> listOf(first, losers[first])
        "Y" -> listOf(first, ties[first])
        "Z" -> listOf(first, winners[first])
        else -> listOf(null)
    }
}

val winners = mapOf(
    Pair("A", "Y"),
    Pair("B", "Z"),
    Pair("C", "X")
)

val ties = mapOf(
    Pair("A", "X"),
    Pair("B", "Y"),
    Pair("C", "Z")
)

val losers = mapOf(
    Pair("A", "Z"),
    Pair("B", "X"),
    Pair("C", "Y")
)

fun gameScore(input: List<String?>) = when (input) {
    listOf("A", "X"), listOf("B", "Y"), listOf("C", "Z") -> 3
    listOf("A", "Z"), listOf("B", "X"), listOf("C", "Y") -> 0
    listOf("A", "Y"), listOf("B", "Z"), listOf("C", "X") -> 6
    else -> 0
}

fun shapeScore(input: String?) = when (input) {
    "X" -> 1
    "Y" -> 2
    "Z" -> 3
    else -> 0
}