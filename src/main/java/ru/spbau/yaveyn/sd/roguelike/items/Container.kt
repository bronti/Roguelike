package ru.spbau.yaveyn.sd.roguelike.items

open class Container(val item: Item, val capacity: Int)
    : Item by item {

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