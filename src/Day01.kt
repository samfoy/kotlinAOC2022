fun main() {
    fun part2(testInput: String) = testInput
        .split("\n\n")
        .map { it.split("\n").sumOf(String::toInt) }
        .sortedDescending()
        .take(3)
        .sum()

    fun part1(testInput: String) = testInput
        .split("\n\n")
        .maxOf { it.split("\n").sumOf(String::toInt) }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01")
    println(part1(testInput))
    println(part2(testInput))
}