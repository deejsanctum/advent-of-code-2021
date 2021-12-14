package advent.of.code.day12

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day12(val input: List<String>) : DaySolution {
    override var day = 12
    private val caveConnections = getCaveConnections()

    override fun getPart1Solution(): Any {
        var cavePaths = mutableListOf<List<String>>()
        cavePaths.add(listOf("start"))
        while (cavePaths.any { it.last() != "end" }) {
            val updated = mutableListOf<List<String>>()
            cavePaths.forEach { path ->
                val last = path.last()
                if (last == "end") {
                    updated.add(path)
                } else {
                    val connectedCaves = caveConnections[last]!!.filter { isValidCave(it, path) }
                    updated.addAll(connectedCaves.map { path + it })
                }
            }
            cavePaths = updated
        }
        return cavePaths.size
    }

    private fun isValidCave(it: String, path: List<String>) = when {
        it == "end" -> true
        it == "start" -> false
        it[0].isLowerCase() -> !path.contains(it)
        else -> true
    }

//    private fun isValidCave2(it: String, path: List<String>) = when {
//        it == "end" -> true
//        it == "start" -> false
//        it[0].isLowerCase() -> {
//            val filter = path.filter { it.length == 1 && it[0].isLowerCase() }
//            val map = filter.groupBy { it }.map { (k, v) -> k to v.size }.toMap()
//            (map[it] ?: 0) == 0 || (map[it] ?: 0) == 1 && map.values.all { it <= 1 }
//        }
//        else -> true
//    }

    override fun getPart2Solution() = "Not implemented"

    private fun getCaveConnections(): MutableMap<String, List<String>> {
        val caveConnections = mutableMapOf<String, List<String>>()
        input.forEach { line ->
            val (a, b) = line.split("-")
            caveConnections[a] = (caveConnections[a] ?: listOf()) + b
            caveConnections[b] = (caveConnections[b] ?: listOf()) + a
        }
        return caveConnections
    }
}

fun main() {
    val input = File(getFilePath("input12.txt")).readLines()
    Day12(input).printSolutions()
}