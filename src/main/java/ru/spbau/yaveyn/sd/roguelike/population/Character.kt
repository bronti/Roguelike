package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import java.awt.Color

abstract class Character
private constructor(override val tile: Tile,
                    private var place: MapWithBorders.Place?,
                    private var container: Container?): GameObject, Destructable()  {

    override fun isOnMap() = place != null

    override fun placeTo(p: MapWithBorders.Place): Boolean {
        place = p
        container = null
        return true
    }

    override fun onMap() = place!!

    override fun pupInto(c: Container): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor(tile: Tile, place: MapWithBorders.Place): this(tile, place, null)
    constructor(tile: Tile, container: Container): this(tile, null, container)
}