package advent.of.code.day4

class BingoProperties(val input: List<String>) {
    var drawNumbers: List<Int> = input[0].split(",").map { it.toInt() }
    var boards: List<Board>

    init {
        boards = getBoardsFromInput()
    }

    private fun getBoardsFromInput(): List<Board> {
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
        return mutableListOf.toList()
    }

}
