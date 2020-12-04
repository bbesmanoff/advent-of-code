import java.io.File;

data class PasswordRule(private val x: Int, private val y: Int, private val ch: Char) {
    fun evaluate1(str: String): Boolean {
        val count = str.count { it == ch }

        return x <= count && count <= y;
    }

    fun evaluate2(str: String): Boolean {
        val ch1 = str.get(x - 1);
        val ch2 = str.get(y - 1);
       
        return (ch == ch1) xor (ch == ch2);
    }
}

fun parseLine(line: String): Pair<PasswordRule, String> {
    val regex = "(\\d+)-(\\d+) (\\w): (\\w+)".toRegex();
    val matches = regex.find(line) ?: throw IllegalArgumentException("$line is not valid")

    val x = matches.groups[1]?.value!!.toInt()
    val y = matches.groups[2]?.value!!.toInt()
    val ch = matches.groups[3]?.value!!.get(0)
    val pw = matches.groups[4]?.value!!

    return PasswordRule(x, y, ch) to pw
}

val input = File("day2.txt").readLines().map(::parseLine)

val day1 = input.count { (rule, pw) -> rule.evaluate1(pw) }
val day2 = input.count { (rule, pw) -> rule.evaluate2(pw) }

println(day1)
println(day2)
