import java.io.File;

fun combinations(input: List<Int>): Sequence<Pair<Int, Int>> {
    val length = input.size;

    return sequence {
        for (aIdx in 0 until length) {
            for (bIdx in (aIdx + 1) until length) {
                yield(input[aIdx] to input[bIdx])
            }
        }
    }
}

val input = File("input.txt").readLines().map(String::toInt)

val day1 = combinations(input)
        .first { (a, b) -> a + b == 2020 }
        .let { (a, b) -> a * b }
println(day1)