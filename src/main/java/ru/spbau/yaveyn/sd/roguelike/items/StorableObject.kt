package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.dungeon.MapWithBorders
import ru.spbau.yaveyn.sd.roguelike.dungeon.Tile


interface StorableObject {
    val weight: Int

    fun isStored(): Boolean
    fun putInto(c: Container): Boolean
    fun takeFromContainer(): Boolean

    fun storage(): Container
}

open class StorableObjectImpl(override val weight: Int): StorableObject {
    private var storage: Container? = null
    override fun isStored() = storage != null

    override fun putInto(c: Container): Boolean {
        if (weight <= c.capacity) {
            storage = c
            return true
        }
        else return false
    }

    override fun takeFromContainer(): Boolean {
        storage = null
        return true
    }

    override fun storage() = storage!!
}