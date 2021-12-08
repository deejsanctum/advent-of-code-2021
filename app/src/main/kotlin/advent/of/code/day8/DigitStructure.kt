package advent.of.code.day8

import advent.of.code.day8.DigitPositions.DOWN
import advent.of.code.day8.DigitPositions.LOWER_LEFT
import advent.of.code.day8.DigitPositions.LOWER_RIGHT
import advent.of.code.day8.DigitPositions.MIDDLE
import advent.of.code.day8.DigitPositions.UP
import advent.of.code.day8.DigitPositions.UPPER_LEFT
import advent.of.code.day8.DigitPositions.UPPER_RIGHT

enum class DigitStructure(val positions: List<DigitPositions>) {
    ZERO(listOf(UP, UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT, DOWN)),
    ONE(listOf(UPPER_RIGHT, LOWER_RIGHT)),
    TWO(listOf(UP, UPPER_RIGHT, MIDDLE, LOWER_LEFT, DOWN)),
    THREE(listOf(UP, UPPER_RIGHT, MIDDLE, LOWER_RIGHT, DOWN)),
    FOUR(listOf(UPPER_LEFT, UPPER_RIGHT, MIDDLE, LOWER_RIGHT)),
    FIVE(listOf(UP, UPPER_LEFT, MIDDLE, LOWER_RIGHT, DOWN)),
    SIX(listOf(UP, UPPER_LEFT, MIDDLE, LOWER_LEFT, LOWER_RIGHT, DOWN)),
    SEVEN(listOf(UP, UPPER_RIGHT, LOWER_RIGHT)),
    EIGHT(listOf(UP, UPPER_LEFT, UPPER_RIGHT, MIDDLE, LOWER_LEFT, LOWER_RIGHT, DOWN)),
    NINE(listOf(UP, UPPER_LEFT, UPPER_RIGHT, MIDDLE, LOWER_RIGHT, DOWN));
}