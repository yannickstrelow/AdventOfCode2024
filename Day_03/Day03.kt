import java.io.File

fun main() {
    val inputAsText = File("Day_03/input.txt").readText()

    // Part 1
    """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(inputAsText)
        .sumOf {
            val (first, second) = it.destructured
            first.toInt() * second.toInt()
        }
        .also { println("The result of multiplications from part one is $it") }

    // Part 2
    var doCalculate = true
    """do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(inputAsText)
        .sumOf {
            when (it.value) {
                "do()" -> {
                    doCalculate = true
                    0
                }

                "don't()" -> {
                    doCalculate = false
                    0
                }

                else -> if (doCalculate) {
                    val (first, second) = it.destructured
                    first.toInt() * second.toInt()
                } else 0
            }
        }
        .also { println("The result of multiplications from part two is $it") }
}