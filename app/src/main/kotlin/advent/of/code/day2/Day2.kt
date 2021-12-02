package advent.of.code.day2

import advent.of.code.shared.DaySolution

class Day2(private val input: List<SubmarinePath>) : DaySolution {
    override var day = 2
    override fun getPart1Solution(): Any {
        var hp = 0
        var depth = 0

        input.forEach {
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

        input.forEach {
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
