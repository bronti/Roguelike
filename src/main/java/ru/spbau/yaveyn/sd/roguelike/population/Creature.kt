package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import java.awt.Color

open class Creature
constructor(private val state: GameState, private val battleUnit: BattleUnit, private val gameObject: GameObject)
    : GameObject by gameObject, BattleUnit by battleUnit{

    fun moveTo(newPlace: MapWithBorders.Place) {
        if (state.dungeon.tile(newPlace).isEmpty()) {
            gameObject.placeTo(newPlace)
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
            tile = Tile.BODY
            state.dieCreature(this)
        }
    }
}