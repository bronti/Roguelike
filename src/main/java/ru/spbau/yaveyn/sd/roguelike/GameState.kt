package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.population.CreaturesHolder
import ru.spbau.yaveyn.sd.roguelike.population.Creature


class GameState(val dungeon: Dungeon) {
    private val creaturesHolder = CreaturesHolder(this)
    fun creatureOnPlace(place: MapWithBorders.Place) = creaturesHolder.creatureOnPlace(place)
    fun dieCreature(creature: Creature) {
        creaturesHolder.removeCreature(creature)
    }
    val player = creaturesHolder.playerCharacter
}