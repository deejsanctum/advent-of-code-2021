package advent.of.code.day1

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day1(input: List<String>) : DaySolution {
    override var day = 1
    private val sonarSweep = input.map { it.toInt() }

    override fun getPart1Solution() = sonarSweep.windowed(2).count { it[1] > it[0] }
    override fun getPart2Solution() = sonarSweep.windowed(3).map { it.sum() }.windowed(2).count { it[1] > it[0] }
}

fun main() {
    Day1(File(getFilePath("input1.txt")).readLines()).printSolutions()
}
