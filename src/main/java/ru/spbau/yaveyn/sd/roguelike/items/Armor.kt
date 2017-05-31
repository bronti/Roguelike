package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.population.Hit

class Armor(val ac: Int) {

    fun affectHit(input: Hit): Hit {
        return Hit(Math.max(0, input.smashing - ac))
    }

    fun getDescription(): String = "ac + $ac"

}