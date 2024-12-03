import java.io.File
import kotlin.math.absoluteValue

fun main() {

    // Part 1
    File("Day_02/input.txt").readLines()
        .map { it -> it.split(" ").map { it.toLong() } }
        .count { it ->

            val isDecreasing = it.first() > it.last()

            it.zipWithNext { previousLevel, level ->
                isSafeLevel(previousLevel, level, isDecreasing)
            }.all { it }
        }.also { println("The number of safe reports in part one is $it") }

    // Part 2
    File("Day_02/input.txt").readLines()
        .map { it -> it.split(" ").map { it.toLong() } }
        .count { levels ->

            levels.indices.any {
                val skipped = levels.toMutableList().apply { removeAt(it) }
                val differences = skipped.zipWithNext { previousLevel, level -> previousLevel - level }
                differences.all { it in -3..3 } && (differences.all { it > 0 } || differences.all { it < 0 })
            }
        }.also { println("The number of safe reports in part two is $it") }
}

fun isSafeLevel(previousLevel: Long, level: Long, isDecreasing: Boolean): Boolean {

    val differenceMatches = (previousLevel - level).absoluteValue in 1..3

    val increaseDecreaseMatches = if (isDecreasing) previousLevel > level else previousLevel < level
    return differenceMatches && increaseDecreaseMatches
}