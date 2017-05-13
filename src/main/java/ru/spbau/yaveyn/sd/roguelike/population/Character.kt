package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.items.Container
import ru.spbau.yaveyn.sd.roguelike.items.StorableObject
import ru.spbau.yaveyn.sd.roguelike.items.StorableObjectImpl

class Character
private constructor(val onMapObject: OnMapObject,
                    private val  innerWeight: Int,
                    private var place: MapWithBorders.Place?,
                    private var container: Container?)
    : OnMapObject by onMapObject, Destructable(), StorableObject {

    override val weight: Int
        get() = innerWeight //todo: sum weights + weight

    constructor(tile: Tile, weight: Int, place: MapWithBorders.Place)
            : this(OnMapObjectImpl(tile), weight, place, null)

}