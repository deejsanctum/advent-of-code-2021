package advent.of.code

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

fun main() {
    Day1(File(getFilePath("input1.txt")).readLines().map { it.toInt() }).printSolutions()
}

class Day1(private val input: List<Int>): DaySolution {
    override fun getPart1Solution() = input.windowed(2).count { it[1] > it[0] }
    override fun getPart2Solution() = input.windowed(3).map { it.sum() }.windowed(2).count { it[1] > it[0] }
}
