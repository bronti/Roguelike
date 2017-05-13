package ru.spbau.yaveyn.sd.roguelike.items

class ItemBuilder(val weight: Int, val description: String) {

    private var armor: Armor? = null
    private var melee: Melee? = null

    fun withArmor(armor: Armor) {
        this.armor = armor
    }

    fun withMelee(melee: Melee) {
        this.melee = melee
    }

    fun build(): Item {
        return ItemImpl(weight, description, armor, melee)
    }
}