package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.Controller
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.PLAYER_WEIGHT
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.items.Container
import ru.spbau.yaveyn.sd.roguelike.items.Item
import java.util.logging.Level
import java.util.logging.Logger

class PlayerCharacter (state: GameState, battleUnit: BattleUnit, onMapObject: OnMapObject)
    : Creature(state, battleUnit, onMapObject, PLAYER_WEIGHT, "Brave and Mighty Player") {
    private val logger = Logger.getLogger(Controller::class.java.name)


    var watchingTo: MapWithBorders.Place? = null
        private set

    var view = "nothing interesting here"
        private set
        get() = if (watchingTo == null) "nothing interesting here" else state.watchAt(watchingTo!!)

    fun watchAt(place: MapWithBorders.Place) {
        watchingTo = place
    }

    fun getItem() {
        if (watchingTo == null) return
        val container = state.onPlace(watchingTo!!)
        if (container == null || container !is Container || container.activeItem == null) return
        logger.log(Level.INFO, "Player got item: ${container.activeItem!!.getDescription()}")
        if (addItem(container.activeItem!!)) container.aquireActiveItem()
    }

    fun dropItem() {
        if (watchingTo == null) return
        val container = state.onPlace(watchingTo!!)
        if (container == null || container !is Container) return
        if (sack.activeItem == null) return
        if (container.put(sack.activeItem!!)) {
            logger.log(Level.INFO, "Player dropped item: ${sack.activeItem!!.getDescription()}")
            removeActiveItem()
        }
    }

    override fun checkedDie() {
        if (isDestructed()) {
            logger.log(Level.INFO, "Player died")
        }
    }

}
