package advent.of.code.day10

enum class ChunkType(
    val char: Char,
    val closing: Char? = null,
    val illegalValue: Int? = null,
    val pointValue: Int? = null,
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
