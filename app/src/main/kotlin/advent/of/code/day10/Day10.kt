package advent.of.code.day10

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

enum class ChunkType(
    val char: Char,
    val closing: Char? = null,
    val illegalValue: Int? = null,
    pointValue: Int? = null,
) {
    OPEN_BRACKETS('{', '}'),
    OPEN_CURVED_PARENTHESIS('(', ')'),
    OPEN_SQUARED_PARENTHESIS('[', ']'),
    OPEN_TAG('<', '>'),
    CLOSE_CURVED_PARENTHESIS(')', null, 3, 1),
    CLOSE_SQUARED_PARENTHESIS(']', null, 57, 2),
    CLOSE_BRACKETS('}', null, 1197, 3),
    CLOSE_TAG('>', null, 25137, 4);
}

fun getChunkTypeFrom(char: Char) = ChunkType.values().find { it.char == char }!!

class Day10(val input: List<String>) : DaySolution {
    override var day = 10

    override fun getPart1Solution(): Any {
        val map = input.map { getIllegalCharacterValue(it) }.mapNotNull {
            when (it) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0
            }
        }.sum()
        return map
    }

    private fun getIllegalCharacterValue(line: String): Char? {
        val currentState = mutableListOf<ChunkType>()
        for (c in line) {
            when (val chunkType = getChunkTypeFrom(c)) {
                ChunkType.OPEN_BRACKETS,
                ChunkType.OPEN_TAG,
                ChunkType.OPEN_SQUARED_PARENTHESIS,
                ChunkType.OPEN_CURVED_PARENTHESIS,
                -> currentState.add(chunkType)
                else -> {
                    if (currentState.last().closing != chunkType.char) {
                        return chunkType.char
                    } else {
                        currentState.removeLast()
                    }
                }
            }
        }
        return null
    }

    override fun getPart2Solution(): Any {
        val map = input.filter { getIllegalCharacterValue(it) == null }
            .map { line ->
                val currentState = mutableListOf<ChunkType>()
                line.forEach { c ->
                    when (val chunkType = getChunkTypeFrom(c)) {
                        ChunkType.OPEN_BRACKETS,
                        ChunkType.OPEN_TAG,
                        ChunkType.OPEN_SQUARED_PARENTHESIS,
                        ChunkType.OPEN_CURVED_PARENTHESIS,
                        -> currentState.add(chunkType)
                        else -> currentState.removeLast()
                    }
                }
                return@map currentState.map { it.closing }.reversed().fold(0L) { acc, c ->
                    acc * 5 + when (c) {
                        ')' -> 1
                        ']' -> 2
                        '}' -> 3
                        '>' -> 4
                        else -> 0
                    }
                }
            }.sorted()
        return map[Math.floorDiv(map.size, 2)]
    }
}

fun main() {
    Day10(listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]",
    )).printSolutions()
    Day10(File(getFilePath("input10.txt")).readLines()).printSolutions()
}