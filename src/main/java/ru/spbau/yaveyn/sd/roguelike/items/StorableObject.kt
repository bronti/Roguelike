package ru.spbau.yaveyn.sd.roguelike.items


/**
 * Object which can be stored in container.
 */
interface StorableObject {
    val weight: Int

    fun isInContainer(): Boolean

    /**
     * Put object into given container.
     */
    fun putInto(c: Container): Boolean

    /**
     * Remove object from the container.
     */
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