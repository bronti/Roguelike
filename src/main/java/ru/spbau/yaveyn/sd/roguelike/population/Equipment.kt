package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.items.Armor
import ru.spbau.yaveyn.sd.roguelike.items.Item
import ru.spbau.yaveyn.sd.roguelike.items.Melee

class Equipment {

    private val armor = HashSet<Armor>()
    private val melee = HashSet<Melee>()

    fun removeItem(item: Item) {
        if (item.isMelee()) melee.remove(item.asMelee())
        if (item.isArmor()) armor.remove(item.asArmor())
    }

    fun addItem(item: Item) {
        if (item.isMelee()) melee.add(item.asMelee())
        if (item.isArmor()) armor.add(item.asArmor())
    }

    fun useArmor(h: Hit): Hit {
        return armor.fold(h, { h, a -> a.affectHit(h) })
    }

    fun useMelee(h: Hit): Hit {
        return melee.fold(h, { h, a -> a.affectHit(h) })
    }

    fun totalAc() = armor.fold(0, { h, a -> h + a.ac})
    fun totalMc() = melee.fold(0, { h, m -> h + m.mc})

}