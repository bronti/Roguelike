package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GOBLINS_COUNT
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.goblinBattleUnit
import ru.spbau.yaveyn.sd.roguelike.playerBattleUnit

class CreaturesHolder(private val state: GameState) {
    val playerCharacter: PlayerCharacter = PlayerCharacter(state, playerBattleUnit(), GameObjectImpl(state, Tile.PLAYER_CHARACTER))
    val creatures: ArrayList<Creature> = ArrayList()

    init {
        placeCreature(playerCharacter)
        (1..GOBLINS_COUNT).map { _ -> getGoblin() } .forEach { g -> placeCreature(g) }
    }

    fun creatureOnPlace(place: MapWithBorders.Place) = creatures.firstOrNull { c -> c.getPlace() == place }
    fun removeCreature(creature: Creature) = creatures.remove(creature)

    private fun getGoblin() = Creature(state, goblinBattleUnit(), GameObjectImpl(state, Tile.GOBLIN))

    private fun placeCreature(creature: Creature) {
        val place = getEmptyPlace()
        creature.placeTo(place)
        creatures.add(creature)
    }

    private fun getEmptyPlace(): MapWithBorders.Place
    {
        var place: MapWithBorders.Place
        do {
            place = state.dungeon.getRandomPlace()
        } while (!state.dungeon.tile(place).isEmpty())
        return place
    }
}