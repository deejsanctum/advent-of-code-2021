package advent.of.code.day14

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day14(val input: List<String>) : DaySolution {
    override var day = 14
    val initialPolymer = input[0]
    val polymerInsertionRules = input.subList(2, input.size).associate {
        val (a, b) = Regex("([A-Z]{2}) -> ([A-Z])").find(it)?.destructured ?: error("")
        a to b
    }

    override fun getPart1Solution() = getSolution(10)
    override fun getPart2Solution() = getSolution(40)

    private fun getSolution(count: Int): Long {
        var updatedPolymerCounts = initialPolymer.windowed(2)
            .groupBy { it }
            .map { (k, v) -> k to v.size.toLong() }
            .toMap()
        repeat(count) {
            updatedPolymerCounts = getNewPolymerCounts(updatedPolymerCounts)
        }
        val nTimes = getCharCounts(updatedPolymerCounts)
        return nTimes.maxOf { it.value } / 2 - nTimes.minOf { it.value } / 2 - 1
    }

    private fun getCharCounts(updatedPolymerCounts: Map<String, Long>): MutableMap<Char, Long> {
        val nTimes = mutableMapOf<Char, Long>()
        updatedPolymerCounts.forEach { (k, v) ->
            val (a, b) = k.toList()
            nTimes[a] = (nTimes[a] ?: 0) + v
            nTimes[b] = (nTimes[b] ?: 0) + v
        }
        return nTimes
    }

    private fun getNewPolymerCounts(updatedPolymerCounts: Map<String, Long>): Map<String, Long> {
        val result = mutableMapOf<String, Long>()
        updatedPolymerCounts.forEach { (k, v) ->
            val newPolymerPair = getUpdatedPolymerPart(k)
            if (newPolymerPair.length == 3) {
                val windowed = newPolymerPair.windowed(2)
                result[windowed[0]] = (result[windowed[0]] ?: 0) + v
                result[windowed[1]] = (result[windowed[1]] ?: 0) + v
            } else {
                result[k] = (result[k] ?: 0) + v
            }
        }
        return result
    }

    private fun getUpdatedPolymerPart(it: String) = "${it[0]}${polymerInsertionRules[it] ?: ""}${it[1]}"
}

fun main() {
    Day14(File(getFilePath("input14.txt")).readLines()).printSolutions()
}