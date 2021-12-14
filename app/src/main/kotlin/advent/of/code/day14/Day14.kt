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

    override fun getPart1Solution(): Any {
        var currPolymer = initialPolymer
        repeat(10) {
            currPolymer = getNewPolymer(currPolymer)
        }
        val toMap = currPolymer.toList().groupBy { it }.map { (k, v) -> k to v.size.toLong() }.toMap()
        return toMap.maxOf { it.value } - toMap.minOf { it.value }
    }

    private fun getNewPolymer(polymer: String): String {
        val windowed = polymer.windowed(2).map {
            getUpdatedPolymerPart(it)
        }
        return "${windowed[0]}${
            windowed.subList(1, windowed.size).joinToString("") { it.subSequence(1, it.length) }
        }"
    }

    private fun getUpdatedPolymerPart(it: String) = "${it[0]}${polymerInsertionRules.getOrDefault(it, "")}${it[1]}"

    override fun getPart2Solution() = "Not implemented"

}

fun main() {
    Day14(File(getFilePath("input14.txt")).readLines()).printSolutions()
}