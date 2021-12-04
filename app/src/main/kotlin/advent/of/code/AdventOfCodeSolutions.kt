package advent.of.code

import advent.of.code.day1.Day1
import advent.of.code.day2.Day2
import advent.of.code.day2.SubmarinePath
import advent.of.code.day3.Day3
import advent.of.code.day4.Day4
import advent.of.code.shared.getFilePath
import java.io.File

fun main() {
    Day1(File(getFilePath("input1.txt")).readLines().map { it.toInt() }).printSolutions()
    Day2(File(getFilePath("input2.txt")).readLines().map { SubmarinePath.from(it) }).printSolutions()
    Day3(File(getFilePath("input3.txt")).readLines()).printSolutions()
    Day4(File(getFilePath("input4.txt")).readLines()).printSolutions()
}

