package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.items.Item
import ru.spbau.yaveyn.sd.roguelike.items.StorableObjectImpl
import java.util.*

open class Creature
internal constructor(protected val state: GameState,
                     protected val battleUnit: BattleUnit,
                     protected val onMapObject: OnMapObject,
                     protected val innerWeight: Int,
                     private val description: String)
    : OnMapObject by onMapObject, BattleUnit by battleUnit, StorableObjectImpl(innerWeight) {

    protected var sack = Sack(state)
    protected val equipment = Equipment()

    private val random = Random()

    fun dropSack(): Sack {
        val result = sack
        sack = Sack(state)
        return result
    }

    fun getSackDescription() = sack.getCurrentItemDescription()
    fun getStatsDescription() = "ac = ${equipment.totalAc()}, mc = ${equipment.totalMc() + battleUnit.mc}"
    fun getHealtDescription() = "${getHealth()} / $maxHealth"
    fun getDescription() = "$description, health: ${getHealtDescription()}"

    fun scrollSack() = sack.scroll()

    fun addItem(i: Item): Boolean {
        if (sack.put(i)) {
            equipment.addItem(i)
            return true
        }
        return false
    }

    override fun makeHit() = equipment.useMelee(battleUnit.makeHit())
    override fun takeHit(hit: Hit) = battleUnit.takeHit(equipment.useArmor(hit))

    override val weight: Int
        get() = innerWeight //todo: add stuff

    private fun canMoveTo(newPlace: MapWithBorders.Place) = state.dungeon.tile(newPlace).isEmpty()

    fun moveTo(newPlace: MapWithBorders.Place) {
        if (isDestructed()) return
        if (canMoveTo(newPlace)) {
            if (onMapObject.placeTo(newPlace)) {
                takeFromContainer()
            }
            // todo: storable on map
        }
        else if (state.dungeon.tile(newPlace).isCreature()) {
            val other = state.creatureOnPlace(newPlace)!!
            other.takeHit(makeHit())
            other.checkedDie()
//            takeHit(other.makeHit())
//            checkedDie()
        }
    }

    fun moveToPlayer() {
        if (state.player.isDestructed()) return
        val target = state.player.getPlace()
        if (!isOnMap()) return

        val top = getPlace().shiftedY(1)
        val bot = getPlace().shiftedY(-1)
        val left = getPlace().shiftedX(-1)
        val right = getPlace().shiftedX(1)

        when (target) {
            top -> {
                moveTo(top)
                return
            }
            bot -> {
                moveTo(bot)
                return
            }
            left -> {
                moveTo(left)
                return
            }
            right -> {
                moveTo(right)
                return
            }
        }

        val dests = listOf(top, bot, left, right)

        val goto = dests[random.nextInt(4)]
        if (canMoveTo(goto)) moveTo(goto)
    }

    open fun checkedDie() {
        if (isDestructed()) {
            val place = onMapObject.getPlace()
            onMapObject.takeFromMap()
            sack.placeTo(place)
            state.dieCreature(this)
        }
    }
}