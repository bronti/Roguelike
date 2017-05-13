package ru.spbau.yaveyn.sd.roguelike.items

interface Item: StorableObject {
    fun isArmor(): Boolean
    fun isMelee(): Boolean
}

class ItemImpl
internal constructor (val description: String,
                      val armor: Armor?,
                      val melee: Melee?)
    : Item {

    override fun isArmor() = armor != null
    override fun isMelee() = melee != null
}
