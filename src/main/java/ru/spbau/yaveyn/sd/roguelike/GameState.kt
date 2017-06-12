package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.items.Container
import ru.spbau.yaveyn.sd.roguelike.population.Creature
import ru.spbau.yaveyn.sd.roguelike.population.NonPlayerCharacter


class GameState(val dungeon: Dungeon) {
    private val creaturesHolder = ObjectsHolder(this)
    /**
     * Get creature on the given place.
     */
    fun creatureOnPlace(place: MapWithBorders.Place) = creaturesHolder.creatureOnPlace(place)
    /**
     * Get object on the given place.
     */
    fun onPlace(place: MapWithBorders.Place) = creaturesHolder.onPlace(place)
    /**
     * Remove creature from creatures holder.
     */
    fun dieCreature(creature: Creature) {
        creaturesHolder.removeCreature(creature)
    }

    /**
     * Make PC look at the given place.
     */
    fun watchAt(place: MapWithBorders.Place): String {
        val thing = creaturesHolder.onPlace(place)
        return when (thing) {
            is Creature -> thing.getDescription()
            is Container -> thing.getCurrentItemDescription()
            else -> "nothing interesting here"
        }
    }

    /**
     * PC.
     */
    val player = creaturesHolder.playerCharacter
    /**
     * NPCs.
     */
    val npcs: List<NonPlayerCharacter>
        get() = creaturesHolder.npcs
}