package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.population.BattleUnitImpl
import ru.spbau.yaveyn.sd.roguelike.population.Hit

val screenWidth = 50
val screenHeight = 50
val notesHeight = 10

val ITERS_COUNT_FOR_CAVE_DUNGEON_GENERATION = 20
val GOBLINS_COUNT = 100

val GOBLIN_WEIGHT = 10
val PLAYER_WEIGHT  = 70

fun playerBattleUnit() = BattleUnitImpl(100, Hit(10))
fun goblinBattleUnit() = BattleUnitImpl(30, Hit(10))
