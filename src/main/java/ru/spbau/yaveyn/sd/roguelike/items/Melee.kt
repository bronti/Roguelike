package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.population.Hit


class Melee(val mc: Int) {
    fun affectHit(input: Hit): Hit {
        return Hit(Math.max(0, input.smashing + mc))
    }
}