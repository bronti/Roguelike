package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.PLAYER_CAPACITY
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.getSackItem
import ru.spbau.yaveyn.sd.roguelike.items.Container
import ru.spbau.yaveyn.sd.roguelike.items.ItemBuilder


/**
 * Creature's sack.
 */
class Sack(state: GameState): Container(OnMapObjectImpl(state, Tile.SACK), getSackItem(), PLAYER_CAPACITY)