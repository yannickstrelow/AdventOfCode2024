import java.io.File
import kotlin.math.sign

fun main() {

    val charCoordinates = HashMap<Char, HashSet<Pair<Int, Int>>>()

    // read the coordinates of the relevant letters
    File("Day_04/input.txt").readLines().forEachIndexed { row, line ->
        line.toCharArray().forEachIndexed { column, ch ->
            if (charCoordinates[ch] == null) charCoordinates[ch] = HashSet()
            charCoordinates[ch]?.add(row to column)
        }
    }

    // Part 1
    // create possible XMAS positions and check if the letter coordinate matches
    charCoordinates['X']?.sumOf { (xRow, xColumn) ->

        arrayOf(
            xRow - 1 to xColumn - 1,
            xRow - 1 to xColumn,
            xRow - 1 to xColumn + 1,
            xRow to xColumn - 1,
            xRow to xColumn + 1,
            xRow + 1 to xColumn - 1,
            xRow + 1 to xColumn,
            xRow + 1 to xColumn + 1
        ).filter { it in charCoordinates.getOrDefault('M', HashSet()) }
            .map { (mRow, mColumn) -> xRow + ((mRow - xRow).sign * 2) to xColumn + ((mColumn - xColumn).sign * 2) }
            .filter { it in charCoordinates.getOrDefault('A', HashSet()) }
            .map { (aRow, aColumn) -> xRow + ((aRow - xRow).sign * 3) to xColumn + ((aColumn - xColumn).sign * 3) }
            .count { it in charCoordinates.getOrDefault('S', HashSet()) }

    }.also { println("The number of XMAS words is $it ") }

    // Part 2
    charCoordinates['A']?.sumOf { (aRow, aColumn) ->

        arrayOf(
            aRow - 1 to aColumn - 1,
            aRow - 1 to aColumn + 1,
            aRow + 1 to aColumn - 1,
            aRow + 1 to aColumn + 1
        ).filter { charCoordinates['M']?.contains(it) == true }
            .map { (mRow, mColumn) -> aRow + ((mRow - aRow) * -1) to aColumn + ((mColumn - aColumn) * -1) }
            .count { charCoordinates['S']?.contains(it) == true } / 2

    }.also { println("The number of crossed MAS words is $it") }

}
