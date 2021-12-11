package advent.of.code.day11

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day11(val input: List<String>) : DaySolution {
    override var day = 11

    private fun buildOctopusMap(): List<List<Octopus>> {
        val result = input.map {
            it.map { oct -> Octopus(oct.toString().toInt()) }
        }
        result.forEachIndexed { y, line ->
            line.forEachIndexed { x, octopus ->
                if (y > 0) {
                    octopus.addAdjacentOctopus(result[y - 1][x])
                    if (x > 0) octopus.addAdjacentOctopus(result[y - 1][x - 1])
                    if (x < line.size - 1) octopus.addAdjacentOctopus(result[y - 1][x + 1])
                }
                if (y < result.size - 1) {
                    octopus.addAdjacentOctopus(result[y + 1][x])
                    if (x > 0) octopus.addAdjacentOctopus(result[y + 1][x - 1])
                    if (x < line.size - 1) octopus.addAdjacentOctopus(result[y + 1][x + 1])
                }
                if (x > 0) octopus.addAdjacentOctopus(result[y][x - 1])
                if (x < line.size - 1) octopus.addAdjacentOctopus(result[y][x + 1])
            }
        }
        return result
    }

    override fun getPart1Solution(): Any {
        val octopusMap = buildOctopusMap()
        var nFlashes = 0
        repeat(100) {
            octopusMap.forEach { line -> line.forEach { it.runStep() } }
            octopusMap.forEach { line ->
                line.forEach {
                    if (it.flashed) nFlashes++
                    it.reset()
                }
            }
        }
        return nFlashes
    }

    override fun getPart2Solution(): Any {
        val octopusMap = buildOctopusMap()
        var allFlashed = false
        var step = 0
        while (!allFlashed) {
            step++
            octopusMap.forEach { line -> line.forEach { it.runStep() } }
            allFlashed = octopusMap.all { line -> line.all { it.flashed } }
            octopusMap.forEach { line -> line.forEach { it.reset() } }
        }
        return step
    }
}

fun main() {
    Day11(File(getFilePath("input11.txt")).readLines()).printSolutions()
}