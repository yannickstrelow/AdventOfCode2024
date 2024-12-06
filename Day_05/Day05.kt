import java.io.File

fun main() {

    val instructions = HashSet<Pair<Long, Long>>()
    val printOrders = ArrayList<List<Long>>()

    // read the coordinates of the relevant letters
    File("Day_05/input.txt").readLines().forEach {
        if ("""\d+\|\d+""".toRegex().matches(it)) instructions.add(
            it.substringBefore("|").toLong() to it.substringAfter("|").toLong()
        )

        if ("""(\d+,)+\d+""".toRegex().matches(it)) printOrders.add(it.split(",").map { it.toLong() })
    }

    // Part 1
    val correctOrders = printOrders.filter { order ->
        order.all { orderElement ->
            instructions.all { (before, after) ->
                if (orderElement == before && order.contains(after))
                    order.indexOf(orderElement) < order.indexOf(after)
                else
                    true
            }
        }
    }
    println(
        "The sum of all correct middle numbers is ${
            correctOrders.sumOf { it[(it.size / 2)] }
        }"
    )

    // Part 2
    printOrders.map { order ->
        order.sortedWith { orderElement1, orderElement2 ->
            if (instructions.contains(orderElement1 to orderElement2)) {
                -1
            } else if (instructions.contains(orderElement2 to orderElement1)) {
                1
            } else
                0
        }
    }.filter { !correctOrders.contains(it) }
        .sumOf { it[(it.size / 2)] }
        .also { println("The sum of all sorted middle numbers is $it") }

}
