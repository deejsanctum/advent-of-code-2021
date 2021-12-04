package advent.of.code.day4

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day4(input: List<String>) : DaySolution {
    private val bingoProperties = BingoProperties(input)

    override var day: Int = 4

    override fun getPart1Solution(): Any {
        for (dn in bingoProperties.drawNumbers) {
            for (b in bingoProperties.boards) {
                b.markNumber(dn)
                if (b.bingo) return b.getUnmarkedNumbers().sum() * dn
            }
        }
        return 0
    }

    override fun getPart2Solution(): Any {
        for (dn in bingoProperties.drawNumbers) {
            for (b in bingoProperties.boards) {
                b.markNumber(dn)
                if (bingoProperties.boards.all { it.bingo }) return b.getUnmarkedNumbers().sum() * dn
            }
        }
        return 0
    }
}

fun main() {
    Day4(File(getFilePath("input4.txt")).readLines()).printSolutions()
}
