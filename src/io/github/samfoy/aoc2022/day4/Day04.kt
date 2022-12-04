package io.github.samfoy.aoc2022.day4

import io.github.samfoy.aoc2022.asLines
import io.github.samfoy.aoc2022.getInputForDay
import io.github.samfoy.aoc2022.toPair

typealias Assignment = List<Int>
typealias AssignmentPair = Pair<Assignment, Assignment>

suspend fun main() {

    fun part1(input: List<AssignmentPair>) = input
        .map { it.first.containsAll(it.second) || it.second.containsAll(it.first) }
        .count { it }

    fun part2(input: List<AssignmentPair>) = input
        .map { it.first.intersect(it.second.toSet()) }
        .count { it.isNotEmpty() }

    val input = getInputForDay(4).asLines()
        .map { line ->
            line.split(",")
                .map { it.split("-").map(String::toInt) }
                .map { (it.first()..it.last()).toList() }
                .toPair()
        }

    check(part1(input) == 599)
    check(part2(input) == 928)
}
