package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.dungeon.OnMapObject

/**
 * Container with items.
 */
open class Container(val onMapObject: OnMapObject, val item: Item, val capacity: Int)
    : Item by item, OnMapObject by onMapObject {

    private val items = ArrayList<Item>()

    override val weight: Int
        get() = item.weight + items.sumBy(Item::weight)

    /**
     * Put item into container.
     */
    fun put(i: Item): Boolean {
        if (i.putInto(this)) {
            items.add(i)
            return true
        }
        return false
    }

    private var activeItemIndex = 0
        set(i) {
            if (items.isEmpty()) {
                field = 0
                return
            }
            field = i % items.size
            if (field < 0) field += items.size
        }
        get() {
            activeItemIndex = field
            return field
        }

    /**
     * Active item (this item is shown on description
     * + player can get this item from the container by pressing g while looking at this container).
     */
    val activeItem
        get() = items.getOrNull(activeItemIndex)

    /**
     * Extract active item from container.
     */
    fun aquireActiveItem(): Item? {
        val result = activeItem
        if (result != null) {
            items.removeAt(activeItemIndex)
        }
        return result
    }

    fun getCurrentItemDescription(): String {
        return if (items.isEmpty()) {
            "--"
        }
        else {
            items[activeItemIndex].getDescription()
        }
    }

    /**
     * Change active item to the next item.
     */
    fun scroll() {
        activeItemIndex += 1
    }
}