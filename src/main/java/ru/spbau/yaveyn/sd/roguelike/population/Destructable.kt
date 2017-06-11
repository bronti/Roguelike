package ru.spbau.yaveyn.sd.roguelike.population

interface Destructable {
    val maxHealth: Int
    val health: Int
    fun isDestructed(): Boolean
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
