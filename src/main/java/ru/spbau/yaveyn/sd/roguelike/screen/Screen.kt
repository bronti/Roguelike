package ru.spbau.yaveyn.sd.roguelike.screen

import java.awt.event.KeyEvent
import asciiPanel.AsciiPanel

interface Screen {
    /**
     * Display output on given terminal.
     */
    fun displayOutput(terminal: AsciiPanel)
}