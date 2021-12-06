package advent.of.code.day6

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day6(input: String) : DaySolution {
    override var day = 6
    private val lanternFish = input.split(",").map { it.toInt() }

    override fun getPart1Solution(): Any {
        var currentFish = lanternFish.toMutableList()
        repeat(80) {
            val newFish = mutableListOf<Int>()
            currentFish = currentFish.map {
                var result = it
                if (result == 0) {
                    newFish.add(8)
                    result = 7
                }
                result - 1
            }.toMutableList()
            currentFish.addAll(newFish)
        }
        return currentFish.size
    }

    override fun getPart2Solution(): Any {
        val currentFish = lanternFish.groupBy { it }.map { (k, v) -> k to v.size.toLong() }.toMap().toMutableMap()
        repeat(256) {
            val updates = currentFish.map { (age, amount) ->
                if (age == 0) (6 to amount) else (age - 1 to amount)
            } + (8 to (currentFish[0] ?: 0))
            currentFish.clear()
            updates.forEach { (age, amount) -> currentFish[age] = (currentFish[age] ?: 0) + amount }
        }
        return currentFish.values.sum()
    }
}

fun main() {
    Day6(File(getFilePath("input6.txt")).readText()).printSolutions()
}
