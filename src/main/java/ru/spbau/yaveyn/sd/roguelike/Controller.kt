package ru.spbau.yaveyn.sd.roguelike

import asciiPanel.AsciiPanel
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
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

    override fun keyPressed(key: KeyEvent) {
        screen = when (screen) {
            is WelcomeScreen -> StartScreen()
            is StartScreen   -> {
                state = GameState(makeCaves(mapWidth, mapHeight))
                DungeonScreen(state)
            }
            is DungeonScreen -> {
                val dungeonScreen = screen as DungeonScreen
                when (key.keyCode) {
                    KeyEvent.VK_LEFT   -> state.player.moveTo(dungeonScreen.center().shiftedX(-1))
                    KeyEvent.VK_RIGHT  -> state.player.moveTo(dungeonScreen.center().shiftedX(1))
                    KeyEvent.VK_UP     -> state.player.moveTo(dungeonScreen.center().shiftedY(-1))
                    KeyEvent.VK_DOWN   -> state.player.moveTo(dungeonScreen.center().shiftedY(1))
                }
                if (state.player.isDestructed()) {
                    GameOverScreen(false)
                }
                else when (key.keyCode) {
                    KeyEvent.VK_ESCAPE -> GameOverScreen(false)
                    else               -> screen
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