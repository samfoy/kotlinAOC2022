package io.github.samfoy.aoc2022.day1

import io.github.samfoy.aoc2022.getInputForDay

private fun part1(testInput: String) = testInput
    .split("\n\n")
    .maxOf { it.split("\n").sumOf(String::toInt) }

private fun part2(testInput: String) = testInput
    .split("\n\n")
    .map { it.split("\n").sumOf(String::toInt) }
    .sortedDescending()
    .take(3)
    .sum()

suspend fun main() {
    val testInput = getInputForDay(1)
    println(part1(testInput))
    println(part2(testInput))
}
