package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.items.Item
import ru.spbau.yaveyn.sd.roguelike.items.StorableObjectImpl
import java.util.*

open class Creature
internal constructor(private val state: GameState,
                     private val battleUnit: BattleUnit,
                     private val onMapObject: OnMapObject,
                     private val innerWeight: Int)
    : OnMapObject by onMapObject, BattleUnit by battleUnit, StorableObjectImpl(innerWeight) {

    private val sack = Sack(state)
    private val equipment = Equipment()

    val random = Random()

    fun getEquipmentDescription() = sack.items.map { it -> it.getDescription() }.joinToString()
    fun getStatsDescription() = "ac: ${equipment.totalAc()}, mc: ${equipment.totalMc() + battleUnit.mc}"

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

    fun checkedDie() {
        if (isDestructed()) {
            val place = onMapObject.getPlace()
            onMapObject.takeFromMap()
            sack.placeTo(place)
            state.dieCreature(this)
        }
    }
}