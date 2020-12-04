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

fun combinations3(input: List<Int>): Sequence<Triple<Int, Int, Int>> {
    val length = input.size;

    return sequence {
        for (aIdx in 0 until length) {
            for (bIdx in (aIdx + 1) until (length - 1)) {
                for (cIdx in (bIdx + 1) until length)
                    yield(Triple(input[aIdx], input[bIdx], input[cIdx]))
            }
        }
    }
}

val input = File("input.txt").readLines().map(String::toInt)

val day1 = combinations(input)
        .first { (a, b) -> a + b == 2020 }
        .let { (a, b) -> a * b }
println(day1)

val day2 = combinations3(input)
        .first { (a, b, c) -> a + b + c == 2020 }
        .let { (a, b, c) -> a * b * c }
println(day2)