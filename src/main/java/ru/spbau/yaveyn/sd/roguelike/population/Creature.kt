package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.items.Item
import ru.spbau.yaveyn.sd.roguelike.items.StorableObjectImpl


/**
 * Character.
 */
open class Creature
internal constructor(protected val state: GameState,
                     protected val battleUnit: BattleUnit,
                     protected val onMapObject: OnMapObject,
                     protected val innerWeight: Int,
                     private val description: String)
    : OnMapObject by onMapObject, BattleUnit by battleUnit, StorableObjectImpl(innerWeight) {

    protected var sack = Sack(state)
    protected val equipment = Equipment()

    override val mc: Int
        get() = battleUnit.mc + equipment.totalMc()

    override val ac: Int
        get() = battleUnit.ac + equipment.totalAc()

    /**
     * Drop sack.
     */
    fun dropSack(): Sack {
        val result = sack
        sack = Sack(state)
        return result
    }

    fun getSackDescription() = sack.getCurrentItemDescription()
    fun getStatsDescription() = "ac = $ac, mc = $mc"
    fun getHealthDescription() = "$health / $maxHealth"
    fun getDescription() = "$description, health: ${getHealthDescription()}"

    /**
     * Change active item in sack.
     */
    fun scrollSack() = sack.scroll()

    /**
     * Add item to sack (and equipment).
     */
    fun addItem(i: Item): Boolean {
        if (sack.put(i)) {
            equipment.addItem(i)
            return true
        }
        return false
    }

    /**
     * Remove active item from sack.
     */
    fun removeActiveItem(): Boolean {
        equipment.removeItem(sack.aquireActiveItem()!!)
        return true
    }

    override fun makeHit() = equipment.useMelee(battleUnit.makeHit())
    override fun takeHit(hit: Hit) = battleUnit.takeHit(equipment.useArmor(hit))

    override val weight: Int
        get() = innerWeight + sack.weight

    protected fun canMoveTo(newPlace: MapWithBorders.Place) = state.dungeon.tile(newPlace).isEmpty()

    /**
     * Move to given place (or attack if this place is taken by an enemy.
     */
    fun moveTo(newPlace: MapWithBorders.Place) {
        if (isDestructed()) return
        if (canMoveTo(newPlace)) {
            if (onMapObject.placeTo(newPlace)) {
                takeFromContainer()
            }
        }
        else if (state.dungeon.tile(newPlace).isCreature()) {
            val other = state.creatureOnPlace(newPlace)!!
            other.takeHit(makeHit())
            other.checkedDie()
        }
    }

    protected open fun checkedDie() {
        if (isDestructed()) {
            val place = onMapObject.getPlace()
            onMapObject.takeFromMap()
            sack.placeTo(place)
            state.dieCreature(this)
        }
    }
}