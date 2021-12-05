package advent.of.code.day5

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day5(input: List<String>) : DaySolution {
    override var day = 5
    private val lines = input.map { Line(it) }

    override fun getPart1Solution(): Any {
        return getOverlappingPoints(lines.filter { it.isItHzOrVtLine() })
    }

    override fun getPart2Solution(): Any {
        return getOverlappingPoints(lines)
    }

    private fun getOverlappingPoints(lines: List<Line>): Int {
        val map = lines[0].getLinePoints().associateWith { 1 }.toMutableMap()
        for (i in 1 until lines.size) {
            lines[i].getLinePoints().forEach { map[it] = map.getOrElse(it) { 0 } + 1 }
        }
        return map.filter { it.value > 1 }.size
    }
}

fun main() {
    Day5(File(getFilePath("input5.txt")).readLines()).printSolutions()
}
