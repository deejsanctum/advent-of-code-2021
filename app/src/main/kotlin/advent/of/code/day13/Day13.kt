package advent.of.code.day13

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day13(val input: List<String>) : DaySolution {
    override var day = 13
    private val dotInput = input.filter { Regex("([0-9]+),([0-9]+)").matches(it) }
        .map {
            val (x, y) = Regex("([0-9]+),([0-9]+)").find(it)!!.destructured
            Pair(x.toInt(), y.toInt())
        }.toSet()
    private val foldInput = input.filter { Regex("fold along ([xy])=([0-9]+)").matches(it) }
        .map {
            val (x, y) = Regex("fold along ([xy])=([0-9]+)").find(it)!!.destructured
            Pair(x, y.toInt())
        }

    private fun getFoldedSet(input: Set<Pair<Int, Int>>, firstFold: Pair<String, Int>) = when (firstFold.first) {
        "x" -> foldPaperLeft(input, firstFold.second)
        "y" -> foldPaperUp(input, firstFold.second)
        else -> null
    }

    private fun foldPaperLeft(input: Set<Pair<Int, Int>>, x: Int) = input.map {
        if (it.first > x) {
            val diff = it.first - x
            return@map it.copy(first = x - diff)
        }
        it
    }.toSet()


    private fun foldPaperUp(input: Set<Pair<Int, Int>>, y: Int) = input.map {
        if (it.second > y) {
            val diff = it.second - y
            return@map it.copy(second = y - diff)
        }
        it
    }.toSet()

    override fun getPart1Solution() = getFoldedSet(dotInput, foldInput[0])!!.size

    override fun getPart2Solution(): Any {
        val foldedInput = foldInput.fold(dotInput) { acc, pair -> getFoldedSet(acc, pair) ?: error("") }
        val maxX = foldedInput.maxOf { it.first }
        val maxY = foldedInput.maxOf { it.second }
        val result = (0..maxY).map { y ->
            (0..maxX).map { x ->
                if (foldedInput.find { it.first == x && it.second == y } != null) "#" else "."
            }.toMutableList()
        }
        result.forEach {
            println(it.joinToString(""))
        }
        return "Check above!"
    }
}

fun main() {
    Day13(File(getFilePath("input13.txt")).readLines()).printSolutions()
}