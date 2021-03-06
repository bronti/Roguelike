package ru.spbau.yaveyn.sd.roguelike.population

/**
 * Destructable object.
 */
interface Destructable {
    /**
     * Maximum amount of health.
     */
    val maxHealth: Int
    /**
     * Current amount of health.
     */
    val health: Int
    fun isDestructed(): Boolean
    /**
     * Take a hit.
     */
    fun takeHit(hit: Hit)
}

class DestructableImpl(override val maxHealth: Int): Destructable {
    override var health = maxHealth
        private set

    override fun isDestructed() = health == 0

    override fun takeHit(hit: Hit) {
        health = Math.max(0, health - hit.smashing)
    }
}
