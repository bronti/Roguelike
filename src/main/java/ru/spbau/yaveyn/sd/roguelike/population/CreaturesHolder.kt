package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.*
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.items.Container

class CreaturesHolder(private val state: GameState) {
    val playerCharacter: PlayerCharacter = PlayerCharacter(state, playerBattleUnit(), OnMapObjectImpl(state, Tile.PLAYER_CHARACTER))
    private val creatures: ArrayList<Creature> = ArrayList()
    private val containers: ArrayList<Container> = ArrayList()
    val npcs
        get() = creatures.filter { it != this.playerCharacter }

    init {
        placeCreature(playerCharacter)
        playerCharacter.addItem(getAllmightyPantsu())
        playerCharacter.addItem(getSpikedFistArmor())
        (1..GOBLINS_COUNT).map { _ -> getGoblin() } .forEach { g -> placeCreature(g); g.addItem(getGoblinSword()) }
    }

    fun creatureOnPlace(place: MapWithBorders.Place) = creatures.firstOrNull { c -> c.getPlace() == place }
    fun onPlace(place: MapWithBorders.Place): OnMapObject? =
            containers.firstOrNull { c -> c.getPlace() == place } ?: creatureOnPlace(place)
    fun removeCreature(creature: Creature) {
        creatures.remove(creature)
        containers.add(creature.dropSack())
    }

    private fun getGoblin() = Creature(state, goblinBattleUnit(), OnMapObjectImpl(state, Tile.GOBLIN), GOBLIN_WEIGHT, GOBLIN_DESCR)

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