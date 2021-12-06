package advent.of.code.day2

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day2(input: List<String>) : DaySolution {
    override var day = 2
    private val submarinePath = input.map { SubmarinePath.from(it) }
    override fun getPart1Solution(): Any {
        var hp = 0
        var depth = 0

        submarinePath.forEach {
            when (it.direction) {
                "forward" -> hp += it.distance
                "up" -> depth -= it.distance
                "down" -> depth += it.distance
            }
        }

        return hp * depth
    }

    override fun getPart2Solution(): Any {
        var hp = 0
        var depth = 0
        var aim = 0

        submarinePath.forEach {
            when (it.direction) {
                "forward" -> {
                    hp += it.distance
                    depth += aim * it.distance
                }
                "up" -> aim -= it.distance
                "down" -> aim += it.distance
            }
        }

        return hp * depth
    }

}

fun main() {
    Day2(File(getFilePath("input2.txt")).readLines()).printSolutions()
}
