package org.hexworks.cobalt.databinding.internal.collections

import org.hexworks.cobalt.databinding.api.collection.toProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName")
class DefaultListPropertyTest {

    private val target = NUMBERS.toProperty()

    @Test
    fun When_A_number_is_added_to_target_its_value_should_change() {
        target.add(4)

        assertEquals(listOf(1, 2, 3, 4), target)
    }

    companion object {
        val NUMBERS = listOf(1, 2, 3)
    }
}