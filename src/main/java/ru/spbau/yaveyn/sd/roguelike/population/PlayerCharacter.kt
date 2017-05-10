package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile

class PlayerCharacter(place: MapWithBorders.Place): Character(Tile.PLAYER_CHARACTER, place)