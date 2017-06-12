package ru.spbau.yaveyn.sd.roguelike.items

interface StorableObject {
    val weight: Int

    fun isInContainer(): Boolean
    fun putInto(c: Container): Boolean
    fun takeFromContainer(): Boolean

    fun storage(): Container
}

open class StorableObjectImpl(override val weight: Int) : StorableObject {
    private var storage: Container? = null
    override fun isInContainer() = storage != null

    override fun putInto(c: Container): Boolean {
        if (weight <= c.capacity) {
            storage = c
            return true
        } else return false
    }

    override fun takeFromContainer(): Boolean {
        storage = null
        return true
    }

    override fun storage() = storage!!
}