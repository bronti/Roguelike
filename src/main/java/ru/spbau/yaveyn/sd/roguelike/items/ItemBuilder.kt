package ru.spbau.yaveyn.sd.roguelike.items

/**
 * Builder for Item class
 */
class ItemBuilder(val weight: Int, val description: String) {

    private var armor: Armor? = null
    private var melee: Melee? = null

    /**
     * Set armor.
     */
    fun withArmor(armor: Armor): ItemBuilder {
        this.armor = armor
        return this
    }

    /**
     * Set melee weapon
     */
    fun withMelee(melee: Melee): ItemBuilder {
        this.melee = melee
        return this
    }

    /**
     * Build an item.
     */
    fun build(): Item {
        return ItemImpl(description, weight, armor, melee)
    }
}