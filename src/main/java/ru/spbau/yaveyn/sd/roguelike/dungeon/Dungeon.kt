package ru.spbau.yaveyn.sd.roguelike.dungeon

typealias TileMap = Array<Array<Tile>>

class Dungeon(private val tiles: TileMap): MapWithBorders(tiles.size, tiles[0].size){
    fun tile(p: Place) = if (p.isOutOfBounds()) Tile.OUTER else tiles[p.x][p.y]
    fun setTile(p: Place, tile: Tile) {
        tiles[p.x][p.y] = tile
    }
}


