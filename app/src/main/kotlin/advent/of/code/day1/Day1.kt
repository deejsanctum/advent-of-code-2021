package advent.of.code.day1

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day1(private val input: List<Int>) : DaySolution {
    override var day = 1

    override fun getPart1Solution() = input.windowed(2).count { it[1] > it[0] }
    override fun getPart2Solution() = input.windowed(3).map { it.sum() }.windowed(2).count { it[1] > it[0] }
}

fun main() {
    Day1(File(getFilePath("input1.txt")).readLines().map { it.toInt() }).printSolutions()
}
