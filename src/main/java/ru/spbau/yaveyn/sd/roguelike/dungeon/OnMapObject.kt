package ru.spbau.yaveyn.sd.roguelike.dungeon

import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile

interface OnMapObject {
    val tile: Tile

    fun isOnMap(): Boolean

    fun placeTo(p: MapWithBorders.Place): Boolean
    fun takeFromMap(): Boolean

    fun onMap(): MapWithBorders.Place
}

open class OnMapObjectImpl(override val tile: Tile) : OnMapObject {

    private var place: MapWithBorders.Place? = null

    override fun isOnMap() = place != null

    override fun placeTo(p: MapWithBorders.Place): Boolean {
        place = p
        return true
    }

    override fun takeFromMap(): Boolean {
        place = null
        return true
    }

    override fun onMap() = place!!
}