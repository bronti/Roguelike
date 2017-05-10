package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.goblinBattleUnit
import ru.spbau.yaveyn.sd.roguelike.playerBattleUnit

class CharacterFactory(private val state: GameState) {
    val playerCharacter: PlayerCharacter = PlayerCharacter(state, playerBattleUnit(), GameObjectImpl(state, Tile.PLAYER_CHARACTER))
    val goblins: List<NonPlayerCharacter> = (1..10).map { _ -> getGoblin() } .toList()

    init {
        playerCharacter.placeTo(getEmptyPlace())
        goblins.forEach { g -> g.placeTo(getEmptyPlace()) }
    }

    private fun getGoblin() = NonPlayerCharacter(state, goblinBattleUnit(), GameObjectImpl(state, Tile.GOBLIN))

    private fun getEmptyPlace(): MapWithBorders.Place
    {
        var place: MapWithBorders.Place
        do {
            place = state.dungeon.getRandomPlace()
        } while (!state.dungeon.tile(place).isEmpty())
        return place
    }
}