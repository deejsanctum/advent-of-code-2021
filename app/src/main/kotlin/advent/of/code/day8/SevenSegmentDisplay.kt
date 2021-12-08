package advent.of.code.day8

import advent.of.code.day8.DigitPositions.DOWN
import advent.of.code.day8.DigitPositions.LOWER_LEFT
import advent.of.code.day8.DigitPositions.LOWER_RIGHT
import advent.of.code.day8.DigitPositions.MIDDLE
import advent.of.code.day8.DigitPositions.UP
import advent.of.code.day8.DigitPositions.UPPER_LEFT
import advent.of.code.day8.DigitPositions.UPPER_RIGHT

class SevenSegmentDisplay(input: String) {
    private val uniqueSignalPatterns: List<String>
    val digitOutput: List<String>
    private val letterPositions: Map<DigitPositions, String>
    private val digitConfigs: Map<Int, String>
    val outputResult: Int

    init {
        val (part1, part2) = input.split(" | ").map { it.split(" ") }
        uniqueSignalPatterns = part1
        digitOutput = part2
        letterPositions = calcLetterPositions()
        digitConfigs = DigitStructure.values()
            .associate { ds -> ds.ordinal to ds.positions.joinToString("") { letterPositions[it]!! } }
        outputResult = digitOutput.map { digOut ->
            digitConfigs.entries.find { it.value.length == digOut.length && it.value.all { c -> digOut.contains(c) } }?.key
                ?: error("")
        }.joinToString("").toInt()
    }

    private fun calcLetterPositions(): Map<DigitPositions, String> {
        val result = mutableMapOf<DigitPositions, String>()
        val mappingForDigit1 = getMappingForDigit1()
        val mappingForDigit4 = getMappingForDigit4()
        val mappingForDigit7 = getMappingForDigit7()
        val mappingForDigit8 = getMappingForDigit8()
        result[UP] = mappingForDigit7.filter { !mappingForDigit1.contains(it) }

        val digitsWithSixLetters = uniqueSignalPatterns.filter { it.length == 6 }
        val missingCharactersInSixLetterDigits =
            mappingForDigit8.filter { c -> !digitsWithSixLetters.all { it.contains(c) } }
        val digitsInFourAndNotInOne = mappingForDigit4.filter { !mappingForDigit1.contains(it) }
        val middle = digitsInFourAndNotInOne.filter { mp -> !digitsWithSixLetters.all { it.contains(mp) } }
        result[MIDDLE] = middle
        result[UPPER_LEFT] = digitsInFourAndNotInOne.filter { !middle.contains(it) }
        val upperRight = mappingForDigit1.filter { mp -> !digitsWithSixLetters.all { it.contains(mp) } }
        result[UPPER_RIGHT] = upperRight
        result[LOWER_RIGHT] = mappingForDigit1.filter { it != upperRight[0] }

        result[LOWER_LEFT] =
            missingCharactersInSixLetterDigits.filter { it.toString() != middle && it.toString() != upperRight }
        result[DOWN] = mappingForDigit8.filter { !result.values.joinToString("").contains(it) }

        return result
    }

    private fun getMappingForDigit1() = uniqueSignalPatterns.find { it.length == 2 } ?: error("")
    private fun getMappingForDigit4() = uniqueSignalPatterns.find { it.length == 4 } ?: error("")
    private fun getMappingForDigit7() = uniqueSignalPatterns.find { it.length == 3 } ?: error("")
    private fun getMappingForDigit8() = uniqueSignalPatterns.find { it.length == 7 } ?: error("")
}