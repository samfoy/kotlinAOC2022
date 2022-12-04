package io.github.samfoy.aoc2022

import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", name)
    .readText()

/**
 * Converts string to md5 hash.
 */
//fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
//    .toString(16)
//    .padStart(32, '0')

fun <T> List<T>.toPair(): Pair<T, T> {
    if (this.size > 2) throw IllegalArgumentException()
    return Pair(this.first(), this.last())
}