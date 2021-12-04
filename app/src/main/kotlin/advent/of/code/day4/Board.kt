package advent.of.code.day4

class Board {
    private val boardRows: MutableList<List<BoardNumber>> = mutableListOf()
    var bingo = false

    fun addBoardRow(input: String) {
        val (c1, c2, c3, c4, c5) = Regex("([0-9]+)[ ]*([0-9]+)[ ]*([0-9]+)[ ]*([0-9]+)[ ]*([0-9]+)")
            .find(input)!!.destructured

        boardRows.add(listOf(c1, c2, c3, c4, c5).map { BoardNumber(it.toInt(), false) })
    }

    fun getUnmarkedNumbers() = boardRows.flatten().filter { !it.marked }.map { it.number }

    fun markNumber(drawNumber: Int) {
        loop@ for (i in 0..4) {
            for (j in 0..4) {
                val boardNumber = boardRows[i][j]
                if (boardNumber.number == drawNumber) {
                    boardNumber.marked = true
                    if (!this.bingo && this.isItBingo()) {
                        bingo = true
                    }
                    break@loop
                }
            }
        }
    }

    private fun isItBingo(): Boolean {
        if (boardRows.any { row -> row.all { it.marked } }) return true

        for (i in 0..4) {
            if (boardRows.map { it[i] }.all { it.marked }) return true
        }
        return false
    }
}
