package ru.spbau.yaveyn.sd.roguelike

import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.population.CharacterFactory


class GameState(val dungeon: Dungeon) {
    private val charFactory = CharacterFactory(this)
    val player = charFactory.playerCharacter
}