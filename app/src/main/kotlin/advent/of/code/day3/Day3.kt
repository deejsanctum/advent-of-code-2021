package advent.of.code.day3

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day3(private val input: List<String>) : DaySolution {
    override var day = 3

    override fun getPart1Solution(): Any {
        val gammaRateBitList = mutableListOf<Int>()
        val epsilonRateBitList = mutableListOf<Int>()
        for (i in 0 until input[0].length) {
            var zeroCount = 0
            var oneCount = 0
            input.forEach {
                val c = it[i].digitToInt()
                if (c == 0) zeroCount++ else oneCount++
            }
            if (zeroCount > oneCount) {
                gammaRateBitList.add(0)
                epsilonRateBitList.add(1)
            } else {
                gammaRateBitList.add(1)
                epsilonRateBitList.add(0)
            }
        }

        return gammaRateBitList.joinToString("").toInt(2) * epsilonRateBitList.joinToString("").toInt(2)
    }

    override fun getPart2Solution(): Any {
        var ogrList = input.toList()
        var co2srList = input.toList()
        var i = 0
        while (i < input[0].length && ogrList.size != 1) {
            val groupBy = ogrList.groupBy { it[i] }
            ogrList =
                if (groupBy['0']!!.size == groupBy['1']!!.size) groupBy['1']!! else groupBy.maxByOrNull { it.value.size }?.value
                    ?: error("")
            i++
        }
        i = 0
        while (i < input[0].length && co2srList.size != 1) {
            val groupBy = co2srList.groupBy { it[i] }
            co2srList =
                if (groupBy['0']!!.size == groupBy['1']!!.size) groupBy['0']!! else groupBy.minByOrNull { it.value.size }?.value
                    ?: error("")
            i++
        }

        return ogrList[0].toInt(2) * co2srList[0].toInt(2)
    }
}

fun main() {
    Day3(File(getFilePath("input3.txt")).readLines()).printSolutions()
}

