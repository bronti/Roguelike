package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.items.StorableObjectImpl

open class Creature
internal constructor(private val state: GameState,
            private val battleUnit: BattleUnit,
            private val onMapObject: OnMapObject,
            private val innerWeight: Int)
    : OnMapObject by onMapObject, BattleUnit by battleUnit, StorableObjectImpl(innerWeight) {


    override val weight: Int
        get() = innerWeight //todo: add stuff

    fun moveTo(newPlace: MapWithBorders.Place) {
        if (isDestructed()) return
        if (state.dungeon.tile(newPlace).isEmpty()) {
            if (onMapObject.placeTo(newPlace)) {
                takeFromContainer()
            }
            // todo: storable on map
        }
        else if (state.dungeon.tile(newPlace).isCreature()) {
            val other = state.creatureOnPlace(newPlace)!!
            makeHit(other)
            other.checkedDie()
            other.makeHit(this)
            checkedDie()
        }
    }

    fun checkedDie() {
        if (isDestructed()) {
            onMapObject.tile = Tile.BODY
            state.dieCreature(this)
        }
    }
}