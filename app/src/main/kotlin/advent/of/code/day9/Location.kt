package advent.of.code.day9

class Location(heights: List<List<Int>>, x: Int, y: Int) {
    val height = heights[y][x]
    val adjacentHeights: List<Int>
    val isLowPoint: Boolean
    val riskLevel: Int
    val basinNumbers: List<Int> = listOf()

    init {
        val maxY = heights.size - 1
        val maxX = heights[0].size - 1
        val tmp = mutableListOf<Int>()
        if (y < maxY) {
            tmp.add(heights[y + 1][x])
        }
        if (y > 0) {
            tmp.add(heights[y - 1][x])
        }
        if (x > 0) {
            tmp.add(heights[y][x - 1])
        }
        if (x < maxX) {
            tmp.add(heights[y][x + 1])
        }
        adjacentHeights = tmp.toList()
        isLowPoint = adjacentHeights.all { it > height }
        riskLevel = 1 + height
    }
}