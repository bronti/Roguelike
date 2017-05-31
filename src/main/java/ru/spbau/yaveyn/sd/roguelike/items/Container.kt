package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject

open class Container(val onMapObject: OnMapObject, val item: Item, val capacity: Int)
    : Item by item, OnMapObject by onMapObject {

    val items = ArrayList<Item>()

    override val weight: Int
        get() = item.weight + items.sumBy(Item::weight)

    fun put(i: Item): Boolean {
        if (i.doPutInto(this)) {
            items.add(i)
            return true
        }
        return false
    }
}