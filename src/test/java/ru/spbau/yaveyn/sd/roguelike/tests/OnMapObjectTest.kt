package ru.spbau.yaveyn.sd.roguelike.tests

import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObjectImpl
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import ru.spbau.yaveyn.sd.roguelike.dungeon.generation.makeCaves
import ru.spbau.yaveyn.sd.roguelike.screen.DungeonScreen
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders


class OnMapObjectTest {

    @Test
    fun testSimple() {
        val state = GameState(makeCaves(15, 16))
        val obj = OnMapObjectImpl(state, Tile.GOBLIN)

        assert.that(obj.isOnMap(), equalTo(false))

        var place: MapWithBorders.Place
        do {
            place = state.dungeon.getRandomPlace()
        } while (!state.dungeon.tile(place).isEmpty())

        assert.that(obj.placeTo(place), equalTo(true))
        assert.that(obj.getPlace(), equalTo(place))
        assert.that(obj.takeFromMap(), equalTo(true))

        do {
            place = state.dungeon.getRandomPlace()
        } while (state.dungeon.tile(place).isEmpty())

        assert.that(obj.placeTo(place), equalTo(false))
        assert.that(obj.isOnMap(), equalTo(false))
    }
}