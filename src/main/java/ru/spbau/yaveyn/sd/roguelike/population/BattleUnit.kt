package ru.spbau.yaveyn.sd.roguelike.population

interface BattleUnit: Destructable {
    fun makeHit(other: Destructable)
}

class BattleUnitImpl
private constructor(private val health: Destructable, private val baseHit: Hit)
    : BattleUnit, Destructable by health {

    constructor(maxHealth: Int, baseHit: Hit): this(DestructableImpl(maxHealth), baseHit)

    override fun makeHit(other: Destructable) {
        if (!isDestructed()) other.takeHit(baseHit)
    }
}

data class Hit(val smashing: Int)