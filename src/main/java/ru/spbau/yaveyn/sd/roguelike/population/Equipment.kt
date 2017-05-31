package ru.spbau.yaveyn.sd.roguelike.population

import ru.spbau.yaveyn.sd.roguelike.items.Armor
import ru.spbau.yaveyn.sd.roguelike.items.Melee

class Equipment {

    val armor = ArrayList<Armor>()
    val melee = ArrayList<Melee>()

    fun useArmor(h: Hit): Hit {
        return armor.fold(h, { h, a -> a.affectHit(h) })
    }

    fun useMelee(h: Hit): Hit {
        return melee.fold(h, { h, a -> a.affectHit(h) })
    }

    fun totalAc() = armor.fold(0, { h, a -> h + a.ac})
    fun totalMc() = melee.fold(0, { h, m -> h + m.mc})

}