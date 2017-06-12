package ru.spbau.yaveyn.sd.roguelike.screen

import asciiPanel.AsciiPanel
import java.awt.event.KeyEvent
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
import ru.spbau.yaveyn.sd.roguelike.screenHeight
import ru.spbau.yaveyn.sd.roguelike.screenWidth

class StartScreen : Screen {

    override fun displayOutput(terminal: AsciiPanel) {
        terminal.writeCenter("The Roguelike", 1)
        terminal.writeCenter("(c) Anya Yaveyn", 11)
        terminal.writeCenter("-- press any key --", 21)
    }

}