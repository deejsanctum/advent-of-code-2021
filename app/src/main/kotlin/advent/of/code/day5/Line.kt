package advent.of.code.day5

class Line(input: String) {
    private val x1: Int
    private val y1: Int
    private val x2: Int
    private val y2: Int

    init {
        val (x1, y1, x2, y2) = Regex("([0-9]+),([0-9]+) -> ([0-9]+)+,([0-9]+)").find(input)!!.destructured
        this.x1 = x1.toInt()
        this.y1 = y1.toInt()
        this.x2 = x2.toInt()
        this.y2 = y2.toInt()
    }

    fun isItHzOrVtLine() = (x1 == x2 && y1 != y2) || (y1 == y2 && x1 != x2)

    fun getLinePoints(): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        if (x1 == x2) {
            val diff = if (y2 < y1) -1 else 1
            var i = y1
            do {
                result.add(Pair(x1, i))
                i += diff
            } while (i != y2)
        } else if (y1 == y2) {
            val diff = if (x2 < x1) -1 else 1
            var i = x1
            do {
                result.add(Pair(i, y1))
                i += diff
            } while (i != x2)
        } else { // diagonal
            val yDiff = if (y2 < y1) -1 else 1
            val xDiff = if (x2 < x1) -1 else 1
            var x = x1
            var y = y1
            do {
                result.add(Pair(x, y))
                x += xDiff
                y += yDiff
            } while (x != x2)
        }
        result.add(Pair(x2, y2))
        return result.toList()
    }
}
