fun main() {
    fun part1(input: List<String>): Int = input
        .map { it.half() }
        .map { it.common() }
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

fun Pair<CharSequence, CharSequence>.common(): Char =
    this.first.toList().intersect(this.second.toList().toSet()).first()

fun String.half(): Pair<CharSequence, CharSequence> =
    Pair(this.subSequence(0, this.length / 2), this.subSequence(this.length / 2, this.length))

fun Char.getPriority(): Int {
    return if (this.isLowerCase()) {
        this.code - 96
    } else {
        this.code - 38
    }
}
