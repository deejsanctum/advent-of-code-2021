package advent.of.code.day7

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File
import kotlin.math.absoluteValue

class CrabSubmarineFuel(private val horizontalPositions: List<Int>) {
    private val minCrabPos = horizontalPositions.minOrNull() ?: error("")
    private val maxCrabPos = horizontalPositions.maxOrNull() ?: error("")

    fun getLeastFuel() = (minCrabPos..maxCrabPos)
        .map { hz -> horizontalPositions.sumOf { (it - hz).absoluteValue } }
        .minOrNull() ?: error("")

    fun getLeastFuelEnhanced() = (minCrabPos..maxCrabPos).map { hz ->
        horizontalPositions.sumOf {
            (1..(it - hz).absoluteValue).foldIndexed(0) { index, acc, _ -> acc + (index + 1) }.toInt()
        }
    }.minOrNull() ?: error("")

}

class Day7(input: String) : DaySolution {
    override var day = 7
    private val hzPos = CrabSubmarineFuel(input.split(",").map { it.toInt() })
    override fun getPart1Solution() = hzPos.getLeastFuel()
    override fun getPart2Solution() = hzPos.getLeastFuelEnhanced()
}

fun main() {
//    Day7("16,1,2,0,4,2,7,1,2,14").printSolutions()
    Day7(File(getFilePath("input7.txt")).readText()).printSolutions()
}