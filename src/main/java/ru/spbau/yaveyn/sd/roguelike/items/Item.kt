package ru.spbau.yaveyn.sd.roguelike.items

/**
 * Item.
 */
interface Item: StorableObject {
    fun isArmor(): Boolean
    fun isMelee(): Boolean

    /**
     * Return corresponding armor.
     */
    fun asArmor(): Armor

    /**
     * Return corresponding melee weapon.
     */
    fun asMelee(): Melee
    fun getDescription(): String
}

class ItemImpl
internal constructor (private val descr: String,
                      private val inner_weight: Int,
                      val armor: Armor?,
                      val melee: Melee?)
    : StorableObjectImpl(inner_weight), Item {

    override fun getDescription(): String {
        var stats = emptyList<String>()
        if (isArmor()) stats += armor!!.getDescription()
        if (isMelee()) stats += melee!!.getDescription()

        return "$descr: ${stats.joinToString(", ")}"
    }

    override fun isArmor() = armor != null
    override fun asArmor() = armor!!

    override fun isMelee() = melee != null
    override fun asMelee() = melee!!
}
