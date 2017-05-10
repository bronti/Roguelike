package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.GameState
import ru.spbau.yaveyn.sd.roguelike.dungeon.Dungeon
import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile
import java.awt.Color

abstract class Character
private constructor(private val state: GameState,
                    private val health: DestructableImpl,
                    private val gameObject: GameObjectImpl)
    : GameObject by gameObject,
        Destructable by health {

    constructor(state: GameState, place:MapWithBorders.Place, tile: Tile, maxHealth: Int)
            : this(state, DestructableImpl(maxHealth), GameObjectImpl(state, tile)) {
        gameObject.placeTo(place)
    }
}