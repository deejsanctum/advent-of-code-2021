package advent.of.code

import advent.of.code.shared.DaySolution
import advent.of.code.shared.getFilePath
import java.io.File

class Day4(input: List<String>) : DaySolution {
    private val bingoProperties = BingoProperties(input)

    override var day: Int = 4

    override fun getPart1Solution(): Any {
        for (dn in bingoProperties.drawNumbers) {
            for (b in bingoProperties.boards) {
                for (i in 0 until 5) {
                    for (j in 0 until 5) {
                        if (b.boardRows[i][j].number == dn) {
                            b.boardRows[i][j].marked = true
                            if (b.isItBingo())
                                return b.getUnmarkedNumbers().sum() * dn
                        }
                    }
                }
            }
        }
        return 0
    }

    override fun getPart2Solution(): Any {
        for (dn in bingoProperties.drawNumbers) {
            for (b in bingoProperties.boards) {
                for (i in 0 until 5) {
                    for (j in 0 until 5) {
                        if (b.boardRows[i][j].number == dn) {
                            b.markNumber(i, j)
                            if (bingoProperties.boards.all { it.bingo }) {
                                return b.getUnmarkedNumbers().sum() * dn
                            }
                        }
                    }
                }
            }
        }
        return 0
    }
}

data class BoardNumber(val number: Int, var marked: Boolean)

data class BingoProperties(
    var drawNumbers: List<Int> = emptyList(),
    var boards: List<Board> = emptyList(),
) {
    constructor(input: List<String>) : this() {
        drawNumbers = input[0].split(",").map { it.toInt() }
        val mutableListOf = mutableListOf<Board>()
        for (i in 2 until input.size step 6) {
            val board = Board()
            board.addBoardRow(input[i])
            board.addBoardRow(input[i + 1])
            board.addBoardRow(input[i + 2])
            board.addBoardRow(input[i + 3])
            board.addBoardRow(input[i + 4])
            mutableListOf.add(board)
        }
        boards = mutableListOf.toList()
    }
}

class Board {
    val boardRows: MutableList<List<BoardNumber>> = mutableListOf()
    var bingo = false

    fun addBoardRow(input: String) {
        val (c1, c2, c3, c4, c5) = Regex("([0-9]+)[ ]*([0-9]+)[ ]*([0-9]+)[ ]*([0-9]+)[ ]*([0-9]+)")
            .find(input)!!.destructured

        boardRows.add(listOf(c1, c2, c3, c4, c5).map { BoardNumber(it.toInt(), false) })
    }

    fun markNumber(i: Int, j: Int) {
        val boardNumber = boardRows[i][j]
        if (!boardNumber.marked) {
            boardNumber.marked = true
            if (!this.bingo && this.isItBingo()) {
                bingo = true
            }
        }
    }

    fun isItBingo(): Boolean {
        if (boardRows.any { row -> row.all { it.marked } }) return true

        for (i in 0..4) {
            if (boardRows.map { it[i] }.all { it.marked }) return true
        }
        return false
    }

    fun getUnmarkedNumbers() = boardRows.flatten().filter { !it.marked }.map { it.number }
}

fun main() {
    Day4(File(getFilePath("input4.txt")).readLines()).printSolutions()
}
