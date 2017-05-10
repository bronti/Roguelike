package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import java.awt.Color

abstract class Character
constructor(private val state: GameState, private val battleUnit: BattleUnit, private val gameObject: GameObject)
    : GameObject by gameObject, BattleUnit by battleUnit