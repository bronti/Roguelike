package ru.spbau.yaveyn.sd.roguelike.tests

import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Rule
import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves

/**
 * Created by bronti on 10.06.17.
 */
class MapWithBordersTest {

    val outOfBounds = Matcher(MapWithBorders.Place::isOutOfBounds)

    @Test
    fun testRandom() {
        val map = makeCaves(100, 123)

        for (i in 1..100) {
            assert.that(map.getRandomPlace(), !outOfBounds)
        }
    }
}