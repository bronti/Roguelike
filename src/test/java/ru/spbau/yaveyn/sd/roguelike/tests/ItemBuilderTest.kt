package ru.spbau.yaveyn.sd.roguelike.tests

import org.junit.Test
import ru.spbau.yaveyn.sd.roguelike.items.ItemBuilder
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.and
import ru.spbau.yaveyn.sd.roguelike.items.Item
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.has
import ru.spbau.yaveyn.sd.roguelike.items.Armor
import ru.spbau.yaveyn.sd.roguelike.items.Melee


fun Item.armorClass() = this.asArmor().ac
fun Item.meleeClass() = this.asMelee().mc

class ItemBuilderTest {

    val isArmor = Matcher(Item::isArmor)
    val isMelee = Matcher(Item::isMelee)


    @Test
    fun testSimple() {
        var builder = ItemBuilder(5, "Item")
        assert.that(builder.build(), !isArmor and ! isMelee)

        builder = builder.withArmor(Armor(5))
        assert.that(builder.build(), isArmor and ! isMelee and has(Item::armorClass, equalTo(5)))

        builder = builder.withMelee(Melee(7))
        assert.that(builder.build(), has(Item::armorClass, equalTo(5)) and has(Item::meleeClass, equalTo(7)))
    }
}