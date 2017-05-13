package ru.spbau.yaveyn.sd.roguelike.items

interface Item: StorableObject {
    fun isArmor(): Boolean
    fun isMelee(): Boolean
}

class ItemImpl
internal constructor (val description: String,
                      private val inner_weight: Int,
                      val armor: Armor?,
                      val melee: Melee?)
    : StorableObjectImpl(inner_weight), Item {

    override fun isArmor() = armor != null
    override fun isMelee() = melee != null
}
