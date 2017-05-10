package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders

class CharacterFactory(private val dungeon: Dungeon) {
    val playerCharacter: PlayerCharacter = PlayerCharacter(getEmptyPlace())

    private fun getEmptyPlace(): MapWithBorders.Place
    {
        var place = dungeon.getRandomPlace()
        do {
            place = dungeon.getRandomPlace()
        } while (!dungeon.tile(place).isEmpty())
        return place
    }
}