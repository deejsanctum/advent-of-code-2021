package advent.of.code.day2

data class SubmarinePath(val direction: String, val distance: Int) {
    companion object {
        fun from(input: String): SubmarinePath {
            val (direction, distance) = Regex("(forward|down|up) ([0-9]+)").find(input)?.destructured
                ?: error("Regex error")
            return SubmarinePath(direction, distance.toInt())
        }
    }
}
