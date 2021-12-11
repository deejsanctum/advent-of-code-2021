package advent.of.code.day11

class Octopus(var currValue: Int) {
    private val adjacentOctopuses: MutableList<Octopus> = mutableListOf()
    var flashed = false

    fun addAdjacentOctopus(oct: Octopus) {
        adjacentOctopuses.add(oct)
    }

    fun runStep() {
        currValue++
        if (!flashed && currValue > 9) {
            flashed = true
            adjacentOctopuses.forEach { it.runStep() }
        }
    }

    fun reset() {
        if (flashed) {
            flashed = false
            currValue = 0
        }
    }

    override fun toString(): String {
        return if (flashed) "0" else "$currValue"
    }


}