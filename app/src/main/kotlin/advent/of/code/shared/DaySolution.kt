package advent.of.code.shared

interface DaySolution {
    fun getPart1Solution(): Any
    fun getPart2Solution(): Any

    fun printSolutions() {
        printSolution(1, this)
        printSolution(2, this)
    }

    private fun printSolution(part: Int, solution: DaySolution) {
        val result = when(part) {
            1 -> solution.getPart1Solution()
            2 -> solution.getPart2Solution()
            else -> error("Invalid part")
        }
        println("Part $part solution: $result")
    }
}
