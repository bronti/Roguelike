package ru.spbau.yaveyn.sd.roguelike.tests

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.or
import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves

class DungeonTest {
    @Test
    fun testSimple() {
        val dungeon = makeCaves(15, 239)
        val place = dungeon.Place(2, 3)

        assert.that(dungeon.tile(place), equalTo(Tile.FLOOR) or equalTo(Tile.WALL))
        dungeon.setTile(place, Tile.GOBLIN)
        assert.that(dungeon.tile(place), equalTo(Tile.GOBLIN))
    }
}