package advent.of.code

import advent.of.code.day1.Day1
import advent.of.code.day10.Day10
import advent.of.code.day11.Day11
import advent.of.code.day13.Day13
import advent.of.code.day14.Day14
import advent.of.code.day2.Day2
import advent.of.code.day3.Day3
import advent.of.code.day4.Day4
import advent.of.code.day5.Day5
import advent.of.code.day6.Day6
import advent.of.code.day7.Day7
import advent.of.code.day8.Day8
import advent.of.code.day9.Day9
import advent.of.code.shared.getFilePath
import java.io.File

fun main() {
    Day1(File(getFilePath("input1.txt")).readLines()).printSolutionsAndTime()
    Day2(File(getFilePath("input2.txt")).readLines()).printSolutionsAndTime()
    Day3(File(getFilePath("input3.txt")).readLines()).printSolutionsAndTime()
    Day4(File(getFilePath("input4.txt")).readLines()).printSolutionsAndTime()
    Day5(File(getFilePath("input5.txt")).readLines()).printSolutionsAndTime()
    Day6(File(getFilePath("input6.txt")).readText()).printSolutionsAndTime()
    Day7(File(getFilePath("input7.txt")).readText()).printSolutionsAndTime()
    Day8(File(getFilePath("input8.txt")).readLines()).printSolutionsAndTime()
    Day9(File(getFilePath("input9.txt")).readLines()).printSolutionsAndTime()
    Day10(File(getFilePath("input10.txt")).readLines()).printSolutionsAndTime()
    Day11(File(getFilePath("input11.txt")).readLines()).printSolutionsAndTime()
    // TODO: Day12
    Day13(File(getFilePath("input13.txt")).readLines()).printSolutionsAndTime()
    Day14(File(getFilePath("input14.txt")).readLines()).printSolutionsAndTime()
}

