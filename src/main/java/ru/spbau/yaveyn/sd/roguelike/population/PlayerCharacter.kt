package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.INITIAL_PLAYER_MAX_HEALTH
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile

class PlayerCharacter(state: GameState, place: MapWithBorders.Place)
    : Character(state, place, Tile.PLAYER_CHARACTER, INITIAL_PLAYER_MAX_HEALTH)