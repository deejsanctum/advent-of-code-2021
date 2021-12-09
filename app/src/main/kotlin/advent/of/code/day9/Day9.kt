package advent.of.code.day9

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Location(heights: List<List<Int>>, x: Int, y: Int) {
    val height = heights[y][x]
    val adjacentHeights: List<Int>
    val isLowPoint: Boolean
    val riskLevel: Int
    val basinNumbers: List<Int> = listOf()

    init {
        val maxY = heights.size - 1
        val maxX = heights[0].size - 1
        val tmp = mutableListOf<Int>()
        if (y < maxY) {
            tmp.add(heights[y + 1][x])
        }
        if (y > 0) {
            tmp.add(heights[y - 1][x])
        }
        if (x > 0) {
            tmp.add(heights[y][x - 1])
        }
        if (x < maxX) {
            tmp.add(heights[y][x + 1])
        }
        adjacentHeights = tmp.toList()
        isLowPoint = adjacentHeights.all { it > height }
        riskLevel = 1 + height
    }
}

class Day9(val input: List<String>) : DaySolution {
    override var day = 9
    private val locations = calcLocations()

    override fun getPart1Solution() = locations.flatten().filter { it.isLowPoint }.sumOf { it.riskLevel }

    override fun getPart2Solution(): Any {
        TODO("Not implemented")
    }

    private fun calcLocations(): List<List<Location>> {
        val heights = mutableListOf<List<Int>>()
        input.forEach { line ->
            heights.add(line.map { it.toString().toInt() })
        }
        return heights.mapIndexed { y, lines ->
            lines.mapIndexed { x, _ ->
                Location(heights, x, y)
            }
        }
    }
}

fun main() {
    val input = File(getFilePath("input9.txt")).readLines()
//    val input = listOf("2199943210", "3987894921", "9856789892", "8767896789", "9899965678")
    Day9(input).printSolutions()
}