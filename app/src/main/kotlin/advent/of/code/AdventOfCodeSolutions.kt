package advent.of.code

import advent.of.code.day1.Day1
import advent.of.code.day2.Day2
import advent.of.code.day3.Day3
import advent.of.code.day4.Day4
import advent.of.code.day5.Day5
import advent.of.code.shared.getFilePath
import java.io.File

fun main() {
    Day1(File(getFilePath("input1.txt")).readLines().map { it.toInt() }).printSolutionsAndTime()
    Day2(File(getFilePath("input2.txt")).readLines()).printSolutionsAndTime()
    Day3(File(getFilePath("input3.txt")).readLines()).printSolutionsAndTime()
    Day4(File(getFilePath("input4.txt")).readLines()).printSolutionsAndTime()
    Day5(File(getFilePath("input5.txt")).readLines()).printSolutionsAndTime()
}

