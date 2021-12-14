package advent.of.code.day9

class Location(val height: Int) {
    val adjacentLocations = mutableListOf<Location>()

    fun addAdjacentLocation(location: Location) {
        adjacentLocations.add(location)
    }

    fun isLowPoint() = adjacentLocations.all { it.height > height }
}