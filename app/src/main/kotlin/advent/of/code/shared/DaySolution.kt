package advent.of.code.shared

interface DaySolution {
    var day: Int
    fun getPart1Solution(): Any
    fun getPart2Solution(): Any

    fun printSolutions() {
        println("== Day $day solutions ==")
        println("Part 1: ${getPart1Solution()}")
        println("Part 2: ${getPart2Solution()}")
    }
}
