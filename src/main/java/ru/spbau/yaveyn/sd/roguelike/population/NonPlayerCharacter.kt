package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.Controller
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.PLAYER_WEIGHT
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.items.Container
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class NonPlayerCharacter (state: GameState,
                          battleUnit: BattleUnit,
                          onMapObject: OnMapObject,
                          innerWeight: Int,
                          description: String)
    : Creature(state, battleUnit, onMapObject, innerWeight, description) {

    private val random = Random()

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

}
