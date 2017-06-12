package ru.spbau.yaveyn.sd.roguelike.items

import ru.spbau.yaveyn.sd.roguelike.population.Hit


/**
 * Melee weapon.
 */
class Melee(val mc: Int) {
    /**
     * Add melee modificator to hit.
     */
    fun affectHit(input: Hit): Hit {
        return Hit(Math.max(0, input.smashing + mc))
    }

    fun getDescription(): String = "mc +$mc"
}