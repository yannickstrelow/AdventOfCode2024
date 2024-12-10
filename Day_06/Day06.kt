import java.io.File
import kotlin.math.absoluteValue

fun main() {

    val mapFile = File("Day_06/input.txt").readLines()
    val map = Array(mapFile.size) {
        CharArray(mapFile[0].length)
    }
    val guardMarks = charArrayOf(
        '^', '>', 'v', '<'
    )
    var guardPosition = Pair<Int, Int>(0, 0)
    mapFile.forEachIndexed { rowIndex, row ->
        map[rowIndex] = row.toCharArray()
        row.toCharArray().forEachIndexed { columnIndex, column ->
            if (guardMarks.contains(column)) guardPosition = rowIndex to columnIndex
        }
    }

    val distinctPositions = HashSet<Pair<Int, Int>>()
    val positionBeforeObstacles = ArrayList<Pair<Int, Int>>()
    while (true) {
        try {
            val guardMark = map[guardPosition.first][guardPosition.second]
            val (rowDirection, columnDirection) = getGuardDirection(guardMark)
            val nextGuardPosition = map[guardPosition.first + rowDirection][guardPosition.second + columnDirection]
            if (nextGuardPosition == '#') {
                val rotatedGuardMark = guardMarks.indexOf(guardMark) + 1
                map[guardPosition.first][guardPosition.second] =
                    if (rotatedGuardMark == guardMarks.size) guardMarks.first() else guardMarks[rotatedGuardMark]

                positionBeforeObstacles.add(guardPosition)
            } else {
                map[guardPosition.first + rowDirection][guardPosition.second + columnDirection] = guardMark
                map[guardPosition.first][guardPosition.second] = 'X'
                distinctPositions.add(guardPosition)
                guardPosition = guardPosition.first + rowDirection to guardPosition.second + columnDirection
            }
        } catch (e: IndexOutOfBoundsException) {
            distinctPositions.add(guardPosition)
            break
        }
    }

    var sum = 0
    positionBeforeObstacles.forEachIndexed { index, (row, column) ->
        if (index + 3 < positionBeforeObstacles.size) {
            val (nextRow, nextColumn) = positionBeforeObstacles[index + 1]
            val (secondNextRow, secondNextColumn) = positionBeforeObstacles[index + 2]
            val (thirdNextRow, thirdNextColumn) = positionBeforeObstacles[index + 2]
            if (
                (row == nextRow && nextColumn == secondNextColumn && (secondNextColumn - thirdNextColumn).absoluteValue > (secondNextColumn - column).absoluteValue)
                ||
                (column == nextColumn && nextRow == secondNextRow && (secondNextRow - thirdNextRow).absoluteValue > (secondNextRow - row).absoluteValue)) {
                sum++
            }
        }
    }

    println("The number of positions of the guard is ${distinctPositions.size}")
    println("The positions are $positionBeforeObstacles")
    println("The number of possible obstacles for a loop is $sum")
}

fun getGuardDirection(char: Char): Pair<Int, Int> {
    return when (char) {
        '^' -> -1 to 0
        '>' -> 0 to 1
        '<' -> 0 to -1
        'v' -> 1 to 0
        else -> 0 to 0
    }
}
