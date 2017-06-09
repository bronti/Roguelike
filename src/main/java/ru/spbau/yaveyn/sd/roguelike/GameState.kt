package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.items.Container
import ru.spbau.yaveyn.sd.roguelike.population.CreaturesHolder
import ru.spbau.yaveyn.sd.roguelike.population.Creature


class GameState(val dungeon: Dungeon) {
    private val creaturesHolder = CreaturesHolder(this)
    fun creatureOnPlace(place: MapWithBorders.Place) = creaturesHolder.creatureOnPlace(place)
    fun onPlace(place: MapWithBorders.Place) = creaturesHolder.onPlace(place)
    fun dieCreature(creature: Creature) {
        creaturesHolder.removeCreature(creature)
        creaturesHolder.containers.add(creature.dropSack())
    }

    fun watchAt(place: MapWithBorders.Place): String {
        val thing = creaturesHolder.onPlace(place)
        return when (thing) {
            is Creature -> thing.getDescription()
            is Container -> thing.getCurrentItemDescription()
            else -> "nothing interesting here"
        }
    }

    val player = creaturesHolder.playerCharacter
    val npcs: List<Creature>
        get() = creaturesHolder.creatures.filter { it != player }
}