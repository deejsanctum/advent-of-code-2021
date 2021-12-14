package advent.of.code

import advent.of.code.day1.Day1
import advent.of.code.day10.Day10
import advent.of.code.day11.Day11
import advent.of.code.day12.Day12
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
    listOf(
        Day1(File(getFilePath("input1.txt")).readLines()),
        Day2(File(getFilePath("input2.txt")).readLines()),
        Day3(File(getFilePath("input3.txt")).readLines()),
        Day4(File(getFilePath("input4.txt")).readLines()),
        Day5(File(getFilePath("input5.txt")).readLines()),
        Day6(File(getFilePath("input6.txt")).readText()),
        Day7(File(getFilePath("input7.txt")).readText()),
        Day8(File(getFilePath("input8.txt")).readLines()),
        Day9(File(getFilePath("input9.txt")).readLines()),
        Day10(File(getFilePath("input10.txt")).readLines()),
        Day11(File(getFilePath("input11.txt")).readLines()),
        Day12(File(getFilePath("input12.txt")).readLines()),
        Day13(File(getFilePath("input13.txt")).readLines()),
        Day14(File(getFilePath("input14.txt")).readLines())
    ).forEach {
        it.printSolutionsAndTime()
    }
}

