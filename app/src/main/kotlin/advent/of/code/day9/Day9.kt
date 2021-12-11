package advent.of.code.day9

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day9(val input: List<String>) : DaySolution {
    override var day = 9
    private val locations = calcLocations()

    override fun getPart1Solution() = locations.flatten().filter { it.isLowPoint }.sumOf { it.riskLevel }

    override fun getPart2Solution(): Any {
        return "Not implemented"
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