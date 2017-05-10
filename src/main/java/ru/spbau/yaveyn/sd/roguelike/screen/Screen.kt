package ru.spbau.yaveyn.sd.roguelike.screen

import java.awt.event.KeyEvent
import asciiPanel.AsciiPanel

interface Screen {
    fun displayOutput(terminal: AsciiPanel)

//    fun onKey(key: KeyEvent): Screen
}