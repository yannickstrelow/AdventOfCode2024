import java.io.File
import kotlin.math.abs

fun main() {
    val (leftEntries, rightEntries) = File("Day_01/input.txt").readLines().map {
        val leftEntry = it.substringBefore(" ")
        val rightEntry = it.substringAfterLast(" ")
        leftEntry.toInt() to rightEntry.toInt()
    }.unzip()

    val summedUpDifference =
        leftEntries.sorted().zip(rightEntries.sorted()).sumOf { (leftEntry, rightEntry) -> abs(leftEntry - rightEntry) }

    println("The result of part one is $summedUpDifference")

    val rightListByNumberOfOccurences = rightEntries.groupingBy { it }.eachCount()

    val similarityScore = leftEntries.sumOf { it * rightListByNumberOfOccurences.getOrDefault(it, 0) }

    println("The result of part two is $similarityScore")
}