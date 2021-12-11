package advent.of.code.day10

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day10(val input: List<String>) : DaySolution {
    override var day = 10

    override fun getPart1Solution() = input.map { getIllegalCharacterValue(it) }
        .mapNotNull { it?.illegalValue ?: 0 }
        .sum()

    private fun getIllegalCharacterValue(line: String): ChunkType? {
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
                        return chunkType
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
                return@map currentState.map { getChunkTypeFrom(it.closing ?: error("")) }
                    .reversed()
                    .fold(0L) { acc, c -> acc * 5 + c.pointValue!! }
            }.sorted()
        return map[Math.floorDiv(map.size, 2)]
    }
}

fun main() {
    Day10(File(getFilePath("input10.txt")).readLines()).printSolutions()
}