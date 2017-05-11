package ru.spbau.yaveyn.sd.roguelike.screen

import java.awt.event.KeyEvent
import asciiPanel.AsciiPanel
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.population.CreaturesHolder
import ru.spbau.yaveyn.sd.roguelike.screenHeight
import ru.spbau.yaveyn.sd.roguelike.screenWidth


class DungeonScreen(private val state: GameState) : Screen {

    fun center() = state.player.getPlace()

    private fun topLeft(): MapWithBorders.Place {
        var topLeft = center().shifted(-screenWidth / 2, -screenHeight / 2)
        if (topLeft.x + screenWidth > state.dungeon.width) {
            topLeft = topLeft.shiftedX(state.dungeon.width - topLeft.x - screenWidth)
        }
        if (topLeft.y + screenHeight > state.dungeon.height) {
            topLeft = topLeft.shiftedY(state.dungeon.height - topLeft.y - screenHeight)
        }
        return topLeft
    }

    override fun displayOutput(terminal: AsciiPanel) {
        displayTiles(terminal)

    }

    private fun displayTiles(terminal: AsciiPanel) {
        for (x in 0..screenWidth - 1) {
            for (y in 0..screenHeight - 1) {
                val p = topLeft().shifted(x, y)

                terminal.write(state.dungeon.tile(p), RelativePlace(p, topLeft()))
            }
        }
    }

    class RelativePlace(val place: MapWithBorders.Place, val topLeft: MapWithBorders.Place)
}