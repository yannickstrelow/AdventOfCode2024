import java.io.File

fun main() {
    val inputAsText = File("Day_03/input.txt").readText()

    // Part 1
    """mul\(\d{1,3},\d{1,3}\)""".toRegex().findAll(inputAsText)
        .map {
            val numberSequence = """\d{1,3}""".toRegex().findAll(it.value)
            numberSequence.first().value.toInt() to numberSequence.last().value.toInt()
        }
        .sumOf { (first, second) -> Math.multiplyExact(first, second) }
        .also { println("The result of multiplications from part one is $it") }

    // Part 2
    var doCalculate = true
    """(do\(\).*?)|(don't\(\).*?)|(mul\(\d{1,3},\d{1,3}\).*?)""".toRegex().findAll(inputAsText)
        .map {
            if (it.value == "do()") {
                doCalculate = true
                0 to 0
            } else if (it.value == "don't()") {
                doCalculate = false
                0 to 0
            } else if (doCalculate) {
                val numberSequence = """\d{1,3}""".toRegex().findAll(it.value)
                numberSequence.first().value.toInt() to numberSequence.last().value.toInt()
            } else 0 to 0
        }
        .sumOf { (first, second) -> Math.multiplyExact(first, second) }
        .also { println("The result of multiplications from part two is $it") }
}