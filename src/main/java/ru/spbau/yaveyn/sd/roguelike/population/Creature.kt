package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.items.Item
import ru.spbau.yaveyn.sd.roguelike.items.StorableObjectImpl

open class Creature
internal constructor(private val state: GameState,
                     private val battleUnit: BattleUnit,
                     private val onMapObject: OnMapObject,
                     private val innerWeight: Int)
    : OnMapObject by onMapObject, BattleUnit by battleUnit, StorableObjectImpl(innerWeight) {

    private val sack = Sack(state)
    private val equipment = Equipment()

    fun addItem(i: Item): Boolean {
        if (sack.put(i)) {
            if (i.isArmor()) {
                equipment.armor.add(i.asArmor())
            }
            if (i.isMelee()) {
                equipment.melee.add(i.asMelee())
            }
            return true
        }
        return false
    }

    override fun makeHit() = equipment.useMelee(battleUnit.makeHit())
    override fun takeHit(hit: Hit) = battleUnit.takeHit(equipment.useArmor(hit))

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
            other.takeHit(makeHit())
            other.checkedDie()
            takeHit(other.makeHit())
            checkedDie()
        }
    }

    fun checkedDie() {
        if (isDestructed()) {
            val place = onMapObject.getPlace()
            onMapObject.takeFromMap()
            sack.placeTo(place)
            state.dieCreature(this)
        }
    }
}