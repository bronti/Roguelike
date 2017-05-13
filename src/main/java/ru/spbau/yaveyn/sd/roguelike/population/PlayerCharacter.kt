package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.PLAYER_WEIGHT
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl

class PlayerCharacter (state: GameState, battleUnit: BattleUnit, onMapObject: OnMapObject)
    : Creature(state, battleUnit, onMapObject, PLAYER_WEIGHT)
