package ru.spbau.yaveyn.sd.roguelike.tests

import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
import ru.spbau.yaveyn.sd.roguelike.population.BattleUnitImpl
import ru.spbau.yaveyn.sd.roguelike.population.Creature
import ru.spbau.yaveyn.sd.roguelike.population.Hit
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import ru.spbau.yaveyn.sd.roguelike.items.Armor
import ru.spbau.yaveyn.sd.roguelike.items.ItemBuilder
import ru.spbau.yaveyn.sd.roguelike.items.Melee
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.has
import ru.spbau.yaveyn.sd.roguelike.population.PlayerCharacter

class CreatureTest {

    @Test
    fun testStsts() {
        val state = GameState(makeCaves(130, 130))
        val goblin = Creature(state, BattleUnitImpl(100, Hit(10)), OnMapObjectImpl(state, Tile.GOBLIN), 67, "descr")
        assert.that(goblin, has(Creature::health, equalTo(100)) and has(Creature::maxHealth, equalTo(100)))
        assert.that(goblin.mc, equalTo(10))
        assert.that(goblin.ac, equalTo(0))
        assert.that(goblin.weight, equalTo(67))
        assert.that(goblin.isDestructed(), equalTo(false))
        assert.that(goblin.isOnMap(), equalTo(false))
    }

    @Test
    fun testSack() {
        val state = GameState(makeCaves(130, 130))
        val charr = PlayerCharacter(state, BattleUnitImpl(100, Hit(10)), OnMapObjectImpl(state, Tile.GOBLIN))
        val item = ItemBuilder(5, "Item").build()
        val armor = ItemBuilder(5, "Armor").withArmor(Armor(10)).build()
        val meleeArmor = ItemBuilder(5, "Melee + Armor").withMelee(Melee(12)).withArmor(Armor(5)).build()

        assert.that(charr, has(Creature::mc, equalTo(10)) and has(Creature::ac, equalTo(0)))
        charr.addItem(item)
        assert.that(charr, has(Creature::mc, equalTo(10)) and has(Creature::ac, equalTo(0)))
        charr.addItem(armor)
        assert.that(charr, has(Creature::mc, equalTo(10)) and has(Creature::ac, equalTo(10)))
        charr.addItem(meleeArmor)
        assert.that(charr, has(Creature::mc, equalTo(22)) and has(Creature::ac, equalTo(15)))
        charr.removeActiveItem()
        assert.that(charr, has(Creature::mc, equalTo(22)) and has(Creature::ac, equalTo(15)))
        charr.removeActiveItem()
        assert.that(charr, has(Creature::mc, equalTo(22)) and has(Creature::ac, equalTo(5)))
        charr.removeActiveItem()
        assert.that(charr, has(Creature::mc, equalTo(10)) and has(Creature::ac, equalTo(0)))
    }

    @Test
    fun testHit() {
        val state = GameState(makeCaves(130, 130))
        val goblin1 = Creature(state, BattleUnitImpl(100, Hit(10)), OnMapObjectImpl(state, Tile.GOBLIN), 23, "")
        val goblin2 = Creature(state, BattleUnitImpl(100, Hit(10)), OnMapObjectImpl(state, Tile.GOBLIN), 45, "")
        goblin2.takeHit(goblin1.makeHit())
        assert.that(goblin1.health, equalTo(100))
        assert.that(goblin2.health, equalTo(90))

        val meleeArmor = ItemBuilder(5, "Melee + Armor").withMelee(Melee(12)).withArmor(Armor(5)).build()
        goblin1.addItem(meleeArmor)
        goblin2.addItem(meleeArmor)
        goblin1.takeHit(goblin2.makeHit())
        assert.that(goblin1.health, equalTo(83))
        assert.that(goblin2.health, equalTo(90))

    }
}
