package org.hexworks.cobalt.databinding.internal.collections

import org.hexworks.cobalt.databinding.api.collection.toProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName")
class DefaultMapPropertyTestJVM {

    private val target = NUMBERS_1_TO_3.toProperty()

    @Test
    fun When_a_number_is_added_to_target_Then_its_value_changes() {
        target.put(4, 4)

        assertEquals(NUMBERS_1_TO_4, target.value)
    }

    @Test
    fun When_target_changes_Then_its_change_is_emitted_as_an_event() {
        var newValue = mapOf<Int, Int>()

        target.onChange {
            newValue = it.newValue
        }

        target.put(4, 4)

        assertEquals(NUMBERS_1_TO_4, newValue)
    }

    @Test
    fun When_target_is_bound_to_other_Then_its_value_changes() {
        val other = NUMBERS_1_TO_4.toProperty()

        target.bind(other)

        assertEquals(NUMBERS_1_TO_4, target.value)
    }

    companion object {
        val NUMBERS_1_TO_3 = mapOf(1 to 1, 2 to 2, 3 to 3)
        val NUMBERS_1_TO_4 = mapOf(1 to 1, 2 to 2, 3 to 3, 4 to 4)
    }
}