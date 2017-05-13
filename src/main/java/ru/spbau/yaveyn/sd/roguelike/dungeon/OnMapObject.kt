package ru.spbau.yaveyn.sd.roguelike.dungeon

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.population.Container

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
        place = null
        return true
    }

    override fun getPlace() = place!!
}

//interface GameObject {
//    var tile: Tile
//
//    fun isOnMap(): Boolean
//    fun isInContainer(): Boolean
//
//    fun placeTo(newPlace: MapWithBorders.Place): Boolean
////    fun pupInto(c: Container): Boolean
//
//    fun getPlace(): MapWithBorders.Place
//}
//
//class GameObjectImpl(private val state: GameState, override var tile: Tile): GameObject {
//
//    private var place: MapWithBorders.Place? = null
//    private var container: Container? = null
//
//    override fun getPlace() = place!!
//
//
//    override fun isOnMap() = place != null
//    override fun isInContainer() = container != null
//
//    override fun placeTo(newPlace: MapWithBorders.Place): Boolean {
//        return if (state.dungeon.tile(newPlace).isEmpty()) {
//            if (isOnMap()) state.dungeon.setTile(place!!, Tile.FLOOR)
//            place = newPlace
//            container = null
//            state.dungeon.setTile(newPlace, tile)
//            true
//        }
//        else false
//    }
////    override fun pupInto(c: Container): Boolean
//}