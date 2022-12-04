package io.github.samfoy.aoc2022.day4

import io.github.samfoy.aoc2022.readInput
import io.github.samfoy.aoc2022.toPair

typealias Assignment = List<Int>
typealias AssignmentPair = Pair<Assignment, Assignment>

fun main() {

    fun part1(input: List<AssignmentPair>) = input
        .map { it.first.containsAll(it.second) || it.second.containsAll(it.first) }
        .count { it }

    fun part2(input: List<AssignmentPair>) = input
        .map { it.first.intersect(it.second.toSet()) }
        .count { it.isNotEmpty() }

    val input = readInput("resources/Day04").lines()
        .filter { it.isNotBlank() }
        .map { line ->
            line.split(",")
                .map { it.split("-").map(String::toInt) }
                .map { (it.first()..it.last()).toList() }
                .toPair()
        }

    check(part1(input) == 599)
    check(part2(input) == 928)
}
