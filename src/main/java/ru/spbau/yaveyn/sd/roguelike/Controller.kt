package ru.spbau.yaveyn.sd.roguelike

import asciiPanel.AsciiPanel
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
import ru.spbau.yaveyn.sd.roguelike.items.Container
import ru.spbau.yaveyn.sd.roguelike.screen.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.system.exitProcess

class Controller(private val repaint:() -> Unit): KeyListener {

    val mapWidth  = 100
    val mapHeight = 100

    // todo:
    private var state = GameState(makeCaves(mapWidth, mapHeight))

    private var screen: Screen = WelcomeScreen()

    private fun makeMove(dest: MapWithBorders.Place) {
        state.player.moveTo(dest)
        state.npcs.forEach { it.moveToPlayer() }
        state.player.watchAt(dest)
    }

    override fun keyPressed(key: KeyEvent) {
        screen = when (screen) {
            is WelcomeScreen -> StartScreen()
            is StartScreen   -> {
                state = GameState(makeCaves(mapWidth, mapHeight))
                DungeonScreen(state)
            }
            is DungeonScreen -> {
                if (state.player.isDestructed() || key.keyCode == KeyEvent.VK_ESCAPE) {
                    GameOverScreen(false)
                }
                else {
                    val dungeonScreen = screen as DungeonScreen
                    when (key.keyCode) {
                        KeyEvent.VK_A -> makeMove(dungeonScreen.center().shiftedX(-1))
                        KeyEvent.VK_D -> makeMove(dungeonScreen.center().shiftedX(1))
                        KeyEvent.VK_W -> makeMove(dungeonScreen.center().shiftedY(-1))
                        KeyEvent.VK_S -> makeMove(dungeonScreen.center().shiftedY(1))
                        KeyEvent.VK_DOWN  -> state.player.watchAt(dungeonScreen.center().shiftedY(1))
                        KeyEvent.VK_UP    -> state.player.watchAt(dungeonScreen.center().shiftedY(-1))
                        KeyEvent.VK_LEFT  -> state.player.watchAt(dungeonScreen.center().shiftedX(-1))
                        KeyEvent.VK_RIGHT -> state.player.watchAt(dungeonScreen.center().shiftedX(1))
                        KeyEvent.VK_I -> state.player.scrollSack()
                        KeyEvent.VK_G -> state.player.getItem()
                        KeyEvent.VK_C -> {
                            val cont = state.onPlace(state.player.watchingTo!!)
                            if (cont != null && cont is Container) {
                                cont.scroll()
                            }
                        }
                        KeyEvent.VK_L -> {
                            state.player.dropItem()
                        }
                    }

                    dungeonScreen
                }
            }
            is GameOverScreen ->
                when (key.keyCode) {
                    KeyEvent.VK_ENTER -> StartScreen()
                    KeyEvent.VK_ESCAPE -> exitProcess(0)
                    else -> screen
                }
            else -> screen
        }
        repaint()
    }

    override fun keyReleased(key: KeyEvent) {}

    override fun keyTyped(e: KeyEvent) {}

    fun refreshOutput(terminal: AsciiPanel) {
        screen.displayOutput(terminal)
    }
}