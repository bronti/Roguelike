package ru.spbau.yaveyn.sd.roguelike.population

interface Destructable {
    val maxHealth: Int
    fun isDestructed(): Boolean
    fun getHealth(): Int
    fun takeHit(hit: Hit)
}

class DestructableImpl(override val maxHealth: Int): Destructable {
    private var health = maxHealth
    override fun getHealth() = health
    override fun isDestructed() = health == 0

    override fun takeHit(hit: Hit) {
        health = Math.max(0, health - hit.smashing)
    }
}