package ru.spbau.yaveyn.sd.roguelike.tests

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
import ru.spbau.yaveyn.sd.roguelike.population.BattleUnitImpl
import ru.spbau.yaveyn.sd.roguelike.population.Creature
import ru.spbau.yaveyn.sd.roguelike.population.Hit

class BattleUnitTest {

    @Test
    fun testStsts() {
        val goblin = BattleUnitImpl(100, Hit(10))
        assert.that(goblin.health, equalTo(100))
        assert.that(goblin.maxHealth, equalTo(100))
        assert.that(goblin.mc, equalTo(10))
        assert.that(goblin.isDestructed(), equalTo(false))
    }

    @Test
    fun testHit() {
        val goblin1 = BattleUnitImpl(100, Hit(10))
        val goblin2 = BattleUnitImpl(101, Hit(11))
        goblin2.takeHit(goblin1.makeHit())
        assert.that(goblin1.health, equalTo(100))
        assert.that(goblin2.health, equalTo(91))
    }
}
