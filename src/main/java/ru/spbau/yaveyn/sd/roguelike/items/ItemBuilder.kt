package ru.spbau.yaveyn.sd.roguelike.items

class ItemBuilder(val weight: Int, val description: String) {

    private var armor: Armor? = null
    private var melee: Melee? = null

    fun withArmor(armor: Armor): ItemBuilder {
        this.armor = armor
        return this
    }

    fun withMelee(melee: Melee): ItemBuilder {
        this.melee = melee
        return this
    }

    fun build(): Item {
        return ItemImpl(description, weight, armor, melee)
    }
}