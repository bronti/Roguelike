package ru.spbau.yaveyn.sd.roguelike.population

interface Destructable {
    fun getHealth(): Int
}

class DestructableImpl(private var maxHealth: Int): Destructable {
    override fun getHealth() = maxHealth
}