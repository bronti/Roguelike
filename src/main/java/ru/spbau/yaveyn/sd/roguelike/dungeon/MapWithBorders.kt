package ru.spbau.yaveyn.sd.roguelike.dungeon

abstract class MapWithBorders(val width: Int, val height: Int) {

    fun getRandomPlace() = Place((Math.random() * width).toInt(), (Math.random() * height).toInt())

    inner class Place(val x: Int, val y: Int) {

        fun isOutOfBounds() = x < 0 || x >= width || y < 0 || y >= height

        fun shiftedX(d: Int) = Place((x + d), y)
        fun shiftedY(d: Int) = Place(x, (y + d))
        fun shifted(dx: Int, dy: Int) = Place((x + dx), (y + dy))

        private fun toBoundedX(t: Int) = Math.max(0, Math.min(t, width - 1))
        private fun toBoundedY(t: Int) = Math.max(0, Math.min(t, height - 1))

        override fun equals(other: Any?) = if (other is Place) x == other.x && y == other.y else false

        override fun hashCode() = 31 * x + y
    }
}