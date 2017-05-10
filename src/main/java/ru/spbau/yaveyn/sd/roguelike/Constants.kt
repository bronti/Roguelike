package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.population.BattleUnitImpl
import ru.spbau.yaveyn.sd.roguelike.population.Hit

val screenWidth = 50
val screenHeight = 50

val ITERS_COUNT_FOR_CAVE_DUNGEON_GENERATION = 20
val INITIAL_PLAYER_MAX_HEALTH = 100

fun playerBattleUnit() = BattleUnitImpl(100, Hit(10))
fun goblinBattleUnit() = BattleUnitImpl(30, Hit(3))