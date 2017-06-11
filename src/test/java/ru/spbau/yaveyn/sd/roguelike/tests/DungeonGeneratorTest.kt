package ru.spbau.yaveyn.sd.roguelike.tests

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves

class DungeonGeneratorTest {

    @Test
    fun testSimple() {
        val dungeon = makeCaves(15, 23)

        assert.that(dungeon.height, equalTo(23))
        assert.that(dungeon.width, equalTo(15))
    }
}