package advent.of.code.day9

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day9(val input: List<String>) : DaySolution {
    override var day = 9
    private val locations = calcLocations()

    override fun getPart1Solution() = getLowPoints().sumOf { it.height + 1 }

    override fun getPart2Solution() = getLowPoints()
        .map { getBasin(it).size }
        .sortedDescending()
        .subList(0, 3)
        .reduce { acc, i -> acc * i }

    private fun getLowPoints() = locations.flatten().filter { it.isLowPoint() }

    private fun getBasin(initialLocation: Location): List<Location> {
        val locations = mutableListOf(initialLocation)
        var edges = listOf(initialLocation)
        while (edges.isNotEmpty()) {
            val newEdges = mutableListOf<Location>()
            edges.forEach { edge ->
                val adjacentLocations = edge.adjacentLocations.filter { !locations.contains(it) && it.height < 9 }
                locations.addAll(adjacentLocations)
                newEdges.addAll(adjacentLocations)
            }
            edges = newEdges
        }
        return locations
    }

    private fun calcLocations(): List<List<Location>> {
        val result = input.mapIndexed { y, line ->
            line.mapIndexed { x, c -> Location(c.toString().toInt()) }
        }
        val maxY = result.size - 1
        val maxX = result[0].size - 1
        result.forEachIndexed { y, row ->
            row.forEachIndexed { x, location ->
                if (y < maxY) {
                    location.addAdjacentLocation(result[y + 1][x])
                }
                if (y > 0) {
                    location.addAdjacentLocation(result[y - 1][x])
                }
                if (x > 0) {
                    location.addAdjacentLocation(result[y][x - 1])
                }
                if (x < maxX) {
                    location.addAdjacentLocation(result[y][x + 1])
                }
            }
        }
        return result.toList()
    }
}

fun main() {
    val input = File(getFilePath("input9.txt")).readLines()
    Day9(input).printSolutions()
}