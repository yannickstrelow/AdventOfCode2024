import java.io.File

fun main() {

    val xCoordinates = HashSet<Pair<Int, Int>>()
    val mCoordinates = HashSet<Pair<Int, Int>>()
    val aCoordinates = HashSet<Pair<Int, Int>>()
    val sCoordinates = HashSet<Pair<Int, Int>>()

    // read the coordinates of the relevant letters
    File("Day_04/input.txt").readLines().forEachIndexed { row, line ->
        line.toCharArray().forEachIndexed { index, ch ->
            when (ch) {
                'X' -> xCoordinates.add(row to index)
                'M' -> mCoordinates.add(row to index)
                'A' -> aCoordinates.add(row to index)
                'S' -> sCoordinates.add(row to index)
            }
        }
    }

    // Part 1
    // create possible XMAS positions and check if the letter coordinate matches
    xCoordinates.sumOf { (row, column) ->

        val possibleMPositions = setOf(
            row - 1 to column - 1,
            row - 1 to column,
            row - 1 to column + 1,
            row to column - 1,
            row to column + 1,
            row + 1 to column - 1,
            row + 1 to column,
            row + 1 to column + 1
        )

        val mPositions = possibleMPositions.filter { it in mCoordinates }
        if (mPositions.isEmpty()) 0

        val possibleAPositions = HashSet<Pair<Int, Int>>()
        if (mPositions.contains(row - 1 to column - 1)) possibleAPositions.add(row - 2 to column - 2)
        if (mPositions.contains(row - 1 to column)) possibleAPositions.add(row - 2 to column)
        if (mPositions.contains(row - 1 to column + 1)) possibleAPositions.add(row - 2 to column + 2)
        if (mPositions.contains(row to column - 1)) possibleAPositions.add(row to column - 2)
        if (mPositions.contains(row to column + 1)) possibleAPositions.add(row to column + 2)
        if (mPositions.contains(row + 1 to column - 1)) possibleAPositions.add(row + 2 to column - 2)
        if (mPositions.contains(row + 1 to column)) possibleAPositions.add(row + 2 to column)
        if (mPositions.contains(row + 1 to column + 1)) possibleAPositions.add(row + 2 to column + 2)

        val aPositions = possibleAPositions.filter { it in aCoordinates }
        if (aPositions.isEmpty()) 0

        val possibleSPositions = HashSet<Pair<Int, Int>>()
        if (aPositions.contains(row - 2 to column - 2)) possibleSPositions.add(row - 3 to column - 3)
        if (aPositions.contains(row - 2 to column)) possibleSPositions.add(row - 3 to column)
        if (aPositions.contains(row - 2 to column + 2)) possibleSPositions.add(row - 3 to column + 3)
        if (aPositions.contains(row to column - 2)) possibleSPositions.add(row to column - 3)
        if (aPositions.contains(row to column + 2)) possibleSPositions.add(row to column + 3)
        if (aPositions.contains(row + 2 to column - 2)) possibleSPositions.add(row + 3 to column - 3)
        if (aPositions.contains(row + 2 to column)) possibleSPositions.add(row + 3 to column)
        if (aPositions.contains(row + 2 to column + 2)) possibleSPositions.add(row + 3 to column + 3)

        possibleSPositions.count { it in sCoordinates }

    }.also { println("The number of XMAS words is $it ") }

    // Part 2
    aCoordinates.sumOf { (row, column) ->

        val possibleMPositions = setOf(
            row - 1 to column - 1,
            row - 1 to column + 1,
            row + 1 to column - 1,
            row + 1 to column + 1
        )

        val mPositions = possibleMPositions.filter { it in mCoordinates }
        if (mPositions.isEmpty()) 0

        val possibleSPositions = HashSet<Pair<Int, Int>>()
        if (mPositions.contains(row - 1 to column - 1)) possibleSPositions.add(row + 1 to column + 1)
        if (mPositions.contains(row - 1 to column + 1)) possibleSPositions.add(row + 1 to column - 1)
        if (mPositions.contains(row + 1 to column + 1)) possibleSPositions.add(row - 1 to column - 1)
        if (mPositions.contains(row + 1 to column - 1)) possibleSPositions.add(row - 1 to column + 1)

        possibleSPositions.count { it in sCoordinates } / 2

    }.also { println("The number of crossed MAS words is $it") }

}
