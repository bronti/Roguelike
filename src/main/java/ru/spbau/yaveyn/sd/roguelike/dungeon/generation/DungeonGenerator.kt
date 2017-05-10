package ru.spbau.yaveyn.sd.roguelike.dungeon.generation

import ru.spbau.yaveyn.sd.roguelike.ITERS_COUNT_FOR_CAVE_DUNGEON_GENERATION
import ru.spbau.yaveyn.sd.roguelike.dungeon.*

// cave generation algorithm is brought from here http://trystans.blogspot.ru/2011/08/roguelike-tutorial-03-scrolling-through.html


fun makeCaves(width: Int, height: Int): Dungeon {
    val builder = DungeonBuilder(width, height)
    builder.randomizeTiles()
    (1..ITERS_COUNT_FOR_CAVE_DUNGEON_GENERATION).forEach { _ -> builder.smooth() }
    return builder.makeDungeon()
}

class DungeonBuilder(width: Int, height: Int): MapWithBorders(width, height) {
    private var tiles: TileMap = Array(width) { Array(height) { Tile.OUTER } }

    fun makeDungeon() = Dungeon(tiles)

    fun randomizeTiles() {
        tiles = Array(width) { Array(height) { if (Math.random() < 0.5) Tile.FLOOR else Tile.WALL } }
    }

    fun smooth() {
        tiles = Array(width) { x -> Array(height) { y ->
                val floorsMinusWalls =
                        (-1..1).fold(0, { acc: Int, ox: Int ->
                            (-1..1).fold(acc, { acc: Int, oy: Int ->
                                acc +
                                    when {
                                        Place(x + ox, y + oy).isOutOfBounds() ->  0
                                        tiles[x + ox][y + oy] == Tile.FLOOR ->  1
                                        else                                -> -1
                                    }
                            })
                        })

                if (floorsMinusWalls >= 0) Tile.FLOOR else Tile.WALL
        }}
    }
}