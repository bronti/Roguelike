package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders

class CharacterFactory(private val state: GameState) {
    val playerCharacter: PlayerCharacter = PlayerCharacter(state, getEmptyPlace())

    private fun getEmptyPlace(): MapWithBorders.Place
    {
        var place: MapWithBorders.Place
        do {
            place = state.dungeon.getRandomPlace()
        } while (!state.dungeon.tile(place).isEmpty())
        return place
    }
}