package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.items.Armor
import ru.spbau.yaveyn.sd.roguelike.items.ItemBuilder
import ru.spbau.yaveyn.sd.roguelike.items.Melee
import ru.spbau.yaveyn.sd.roguelike.population.BattleUnitImpl
import ru.spbau.yaveyn.sd.roguelike.population.Hit

val screenWidth = 50
val screenHeight = 50
val notesHeight = 10

val ITERS_COUNT_FOR_CAVE_DUNGEON_GENERATION = 20
val GOBLINS_COUNT = 100

val GOBLIN_WEIGHT = 10
val PLAYER_WEIGHT  = 70

val PLAYER_CAPACITY  = 30

fun playerBattleUnit() = BattleUnitImpl(100, Hit(10))
fun goblinBattleUnit() = BattleUnitImpl(30, Hit(10))

fun getSackItem() = ItemBuilder(0, "Somebody died here and his equipment is just lying on the ground.").build()

fun getAllmightyPantsu() = ItemBuilder(5, "Armored pantsu.").withArmor(Armor(4)).build()
fun getSpikedFistArmor() = ItemBuilder(5, "Fist armor with spikes.").withArmor(Armor(1)).withMelee(Melee(5)).build()

fun getGoblinSword() = ItemBuilder(5, "Rusty goblin sword.").withMelee(Melee(5)).build()