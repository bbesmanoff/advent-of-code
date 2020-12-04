import java.io.File

enum class Space(private val ch: Char) {
    OPEN('.'),
    TREE('#');

    override fun toString() = "$ch"

    companion object {
        fun fromCh(other: Char): Space {
            return values().first { other == it.ch }
        }
    }
}

fun generateSequence(width: Int, dx: Int, dy: Int): Sequence<Pair<Int, Int>> {
    var x = 0;
    var y = 0;

    return sequence {
        while (true) {
            yield(y to x);

            x = (x + dx) % width;
            y += dy;
        }
    }
}

val map: List<List<Space>> = File("input.txt").readLines()
        .map { line -> line.map { Space.fromCh(it) } }

val mapWidth = map[0].size
val mapHeight = map.size

val day1 = generateSequence(mapWidth, 3, 1)
        .takeWhile { (x, _) -> x < mapHeight }
        .count { (x, y) -> map[x][y] == Space.TREE }

println(day1)

val day2 = listOf(
        generateSequence(mapWidth, 1, 1),
        generateSequence(mapWidth, 3, 1), // expected answer from day1: 286
        generateSequence(mapWidth, 5, 1),
        generateSequence(mapWidth, 7, 1),
        generateSequence(mapWidth, 1, 2)
).map {
    it
            .takeWhile { (x, _) -> x < mapHeight }
            .count { (x, y) -> map[x][y] == Space.TREE }
            .toLong()
}.reduce { acc, v -> acc * v }

println(day2)