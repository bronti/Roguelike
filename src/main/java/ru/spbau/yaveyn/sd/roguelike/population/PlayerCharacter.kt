package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.INITIAL_PLAYER_MAX_HEALTH
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile

// todo: initial settings
class PlayerCharacter (state: GameState, battleUnit: BattleUnit, gameObject: GameObject)
    : Character(state, battleUnit, gameObject)