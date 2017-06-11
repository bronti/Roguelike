package ru.spbau.yaveyn.sd.roguelike.population

interface BattleUnit: Destructable {
    fun makeHit(): Hit
    val mc: Int
    val ac: Int
}

class BattleUnitImpl
private constructor(private val destructable: Destructable, val baseHit: Hit)
    : BattleUnit, Destructable by destructable {

    constructor(maxHealth: Int, baseHit: Hit): this(DestructableImpl(maxHealth), baseHit)

    override fun makeHit(): Hit {
        return if (!isDestructed()) baseHit else EMPTY_HIT
    }

    override val mc: Int
        get() = baseHit.smashing

    override val ac: Int
        get() = 0

}

data class Hit(val smashing: Int)

val EMPTY_HIT = Hit(0)