fun main() {
    fun part1(input: List<String>) = input
        .map { it.chunked(it.length / 2) }
        .map { it.uniqueChars() }
        .sumOf { it.getPriority() }

    fun part2(input: List<String>) = input
        .chunked(3)
        .map { it.uniqueChars() }
        .sumOf { it.getPriority() }

    val input = readInput("Day03").lines()
    println(part1(input))
    println(part2(input))
}

fun List<String>.uniqueChars() = this
    .map { it.toSet() }
    .reduce { a, b -> a.intersect(b) }
    .first()

fun Char.getPriority() = when (this.isLowerCase()) {
    true -> this.code - 96
    false -> this.code - 38
}

