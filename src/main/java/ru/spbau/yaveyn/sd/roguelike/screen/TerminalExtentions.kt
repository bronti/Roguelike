package ru.spbau.yaveyn.sd.roguelike.screen

import asciiPanel.AsciiPanel
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.population.Creature

fun AsciiPanel.write(tile: Tile, p: DungeonScreen.RelativePlace) {
    write(tile.glyph, p.place.x - p.topLeft.x, p.place.y - p.topLeft.y, tile.color)
}