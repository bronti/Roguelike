package ru.spbau.yaveyn.sd.roguelike.screen

import asciiPanel.AsciiPanel
import java.awt.event.KeyEvent


class GameOverScreen(val isWin: Boolean) : Screen {

    override fun displayOutput(terminal: AsciiPanel) {
        val msg = if (isWin) "Well, congrats.." else "You're such a loser!"
        terminal.writeCenter(msg, 11)
        terminal.writeCenter("Press [enter] to restart or [escape] to quit", 21)
    }
}