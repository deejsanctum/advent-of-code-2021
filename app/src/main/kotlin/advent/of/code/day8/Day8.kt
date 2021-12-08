package advent.of.code.day8

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day8(input: List<String>) : DaySolution {
    override var day = 8
    private val entries: List<SevenSegmentDisplay> = input.map { SevenSegmentDisplay(it) }

    override fun getPart1Solution(): Any {
        return entries.fold(0) { acc, sevenSegmentDisplay ->
            acc + sevenSegmentDisplay.digitOutput.count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
        }
    }

    override fun getPart2Solution(): Any {
        return entries.sumOf { it.outputResult }
    }
}

fun main() {
    Day8(File(getFilePath("input8.txt")).readLines()).printSolutions()
}
