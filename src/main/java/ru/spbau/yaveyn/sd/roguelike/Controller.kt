package ru.spbau.yaveyn.sd.roguelike

import asciiPanel.AsciiFont
import asciiPanel.AsciiPanel
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
import ru.spbau.yaveyn.sd.roguelike.screen.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Controller(private val repaint:() -> Unit): KeyListener {

    val mapWidth  = 100
    val mapHeight = 100

    private val state = GameState(makeCaves(mapWidth, mapHeight))

    private var screen: Screen = WelcomeScreen()

    override fun keyPressed(e: KeyEvent) {}

    override fun keyReleased(key: KeyEvent) {
        screen = when (screen) {
            is WelcomeScreen -> StartScreen()
            is StartScreen   -> DungeonScreen(state)
            is DungeonScreen -> {
                val dungeonScreen = screen as DungeonScreen
                when (key.keyCode) {
                    KeyEvent.VK_LEFT   -> state.player.placeTo(dungeonScreen.center().shiftedX(-1))
                    KeyEvent.VK_RIGHT  -> state.player.placeTo(dungeonScreen.center().shiftedX(1))
                    KeyEvent.VK_UP     -> state.player.placeTo(dungeonScreen.center().shiftedY(-1))
                    KeyEvent.VK_DOWN   -> state.player.placeTo(dungeonScreen.center().shiftedY(1))
                }
                when (key.keyCode) {
                    KeyEvent.VK_ESCAPE -> GameOverScreen(false)
                    KeyEvent.VK_ENTER  -> GameOverScreen(true)
                    else               -> screen
                }
            }
            is GameOverScreen -> if (key.keyCode == KeyEvent.VK_ENTER) StartScreen() else screen
            else -> screen
        }
        repaint()
    }

    fun refreshOutput(terminal: AsciiPanel) {
        screen.displayOutput(terminal)
    }

    override fun keyTyped(e: KeyEvent) {}
}