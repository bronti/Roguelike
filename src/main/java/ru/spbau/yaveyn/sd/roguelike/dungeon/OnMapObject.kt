package ru.spbau.yaveyn.sd.roguelike.dungeon

import ru.spbau.yaveyn.sd.roguelike.GameState

interface OnMapObject {
    var tile: Tile

    fun isOnMap(): Boolean

    fun placeTo(newPlace: MapWithBorders.Place): Boolean
    fun takeFromMap(): Boolean

    fun getPlace(): MapWithBorders.Place
}

open class OnMapObjectImpl(private val state: GameState, override var tile: Tile) : OnMapObject {

    private var place: MapWithBorders.Place? = null

    override fun isOnMap() = place != null

    override fun placeTo(newPlace: MapWithBorders.Place): Boolean {
        return if (state.dungeon.tile(newPlace).isEmpty()) {
            if (isOnMap()) state.dungeon.setTile(place!!, Tile.FLOOR)
            place = newPlace
            state.dungeon.setTile(newPlace, tile)
            true
        } else false
    }

    override fun takeFromMap(): Boolean {
        if (place == null) return false
        state.dungeon.setTile(place!!, Tile.FLOOR)
        place = null
        return true
    }

    override fun getPlace() = place!!
}