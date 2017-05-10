package ru.spbau.yaveyn.sd.roguelike.dungeon

import java.awt.Color
import asciiPanel.AsciiPanel

enum class Tile private constructor(val glyph: Char, val color: Color) {
    FLOOR('#', Color(0x112321)),
    WALL('#', AsciiPanel.white),
    OUTER('x', AsciiPanel.brightBlack),
    PLAYER_CHARACTER('@', AsciiPanel.brightGreen);

    fun isEmpty() = this == FLOOR
}

