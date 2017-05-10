package ru.spbau.yaveyn.sd.roguelike.screen

import java.awt.event.KeyEvent
import asciiPanel.AsciiPanel

class WelcomeScreen : Screen {
    override fun displayOutput(terminal: AsciiPanel) {
        terminal.writeCenter("The Roguelike", 1)
        terminal.writeCenter("(c) Anya Yaveyn", 11)
        terminal.writeCenter("-- press any key --", 21)
    }
}