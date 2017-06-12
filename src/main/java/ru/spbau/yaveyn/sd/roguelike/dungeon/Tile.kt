package ru.spbau.yaveyn.sd.roguelike.dungeon

import java.awt.Color
import asciiPanel.AsciiPanel

/**
 * Visual representation of one cell of the map.
 */
enum class Tile constructor(val glyph: Char, val color: Color) {
    FLOOR('#', Color(0x112321)),
    WALL('#', AsciiPanel.white),
    OUTER('x', AsciiPanel.brightBlack),
    PLAYER_CHARACTER('@', AsciiPanel.brightGreen),
    GOBLIN('g', AsciiPanel.brightRed),
    BODY('*', AsciiPanel.red),
    SACK('.', AsciiPanel.brightGreen);

    fun isEmpty() = this == FLOOR
    fun isCreature() = this == PLAYER_CHARACTER || this == GOBLIN
}

