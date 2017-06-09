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
        var topLeft = center().shifted(-screenWidth / 2, -screenHeight / 2).bounded()
        if (topLeft.x + screenWidth > state.dungeon.width) {
            topLeft = topLeft.shiftedX(state.dungeon.width - topLeft.x - screenWidth)
        }
        if (topLeft.y + screenHeight > state.dungeon.height) {
            topLeft = topLeft.shiftedY(state.dungeon.height - topLeft.y - screenHeight)
        }
        return topLeft
    }

    private fun boundString(s: String) = s.substring(0, minOf(screenWidth - 1, s.length))

    override fun displayOutput(terminal: AsciiPanel) {
        displayTiles(terminal)
        if (state.player.isDestructed()) {
            displayDeadInfo(terminal)
        }
        else {
            displayAliveInfo(terminal)
        }
    }

    private fun displayDeadInfo(terminal: AsciiPanel) {
        terminal.write("YOU ARE DEAD! MWAHAHA!! (press any key to continue)", 0, screenHeight + 1)
    }

    private fun displayAliveInfo(terminal: AsciiPanel) {
        val equipmentDescr = "equipment: ${state.player.getSackDescription()}"
        val statsDescr = "stats: ${state.player.getStatsDescription()}"
        val healthDescr = "health: ${state.player.getHealtDescription()}"
        terminal.write(boundString("$healthDescr; $statsDescr"), 0, screenHeight + 1)
        terminal.write(boundString(equipmentDescr), 0, screenHeight + 3)
        terminal.write("--------------------------------------------------------------------------------", 0, screenHeight + 5)
        terminal.write(boundString("view: ${state.player.view}"), 0, screenHeight + 7)
        terminal.write("--------------------------------------------------------------------------------", 0, screenHeight + 9)
        terminal.write("[wasd] to move; arrows to watch;", 0, screenHeight + 11)
        terminal.write("[i] to scroll equipment; [c] to scroll container you watching at;", 0, screenHeight + 13)
        terminal.write("[l] to drop current item into container; [g] to get item from container;", 0, screenHeight + 15)
        terminal.write("[escape] to give up this miserable life.", 0, screenHeight + 17)
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