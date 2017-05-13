package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject

class Container(val item: Item, val capacity: Int)
    : Item by item {

    val items = ArrayList<Item>()

    override val weight: Int
        get() = item.weight + items.sumBy(Item::weight)
}