package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile

interface GameObject {
    val tile: Tile

    fun isOnMap(): Boolean

    fun placeTo(p: MapWithBorders.Place): Boolean
    fun pupInto(c: Container): Boolean

    fun onMap(): MapWithBorders.Place
}