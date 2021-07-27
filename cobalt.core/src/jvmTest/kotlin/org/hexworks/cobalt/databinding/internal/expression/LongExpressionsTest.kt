package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.binding.bindPlusWith
import org.hexworks.cobalt.databinding.api.binding.bindTimesWith
import org.hexworks.cobalt.databinding.api.binding.bindNegate
import org.hexworks.cobalt.databinding.api.binding.bindMinusWith
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName")
class LongExpressionsTest {

    @Test
    fun When_property_is_negated_binding_value_should_be_negative() {
        val prop: Property<Long> = 2L.toProperty()

        val binding = prop.bindNegate()

        assertEquals(expected = -2, actual = binding.value)
    }

    @Test
    fun When_property_is_added_to_other_property_binding_value_should_be_their_sum() {
        val prop: Property<Long> = 2L.toProperty()
        val otherProp: Property<Long> = 4L.toProperty()

        val sum = prop.bindPlusWith(otherProp)

        assertEquals(expected = 6, actual = sum.value)
    }

    @Test
    fun When_sum_binding_is_created_and_property_changes_sum_should_also_change() {
        val prop: Property<Long> = 2L.toProperty()
        val otherProp: Property<Long> = 4L.toProperty()

        val sum = prop.bindPlusWith(otherProp)

        prop.value = 5

        assertEquals(expected = 9, actual = sum.value)
    }

    @Test
    fun When_complex_binding_is_created_and_property_changes_binding_should_also_change() {
        val sumPart0: Property<Long> = 2L.toProperty()
        val sumPart1: Property<Long> = 4L.toProperty()

        val diffPart0: Property<Long> = 8L.toProperty()
        val diffPart1: Property<Long> = 2L.toProperty()

        val sum = sumPart0.bindPlusWith(sumPart1)
        val diff = diffPart0.bindMinusWith(diffPart1)

        val complexBinding = sum.bindTimesWith(diff)

        assertEquals(expected = 36, actual = complexBinding.value)

        diffPart1.value = 7

        assertEquals(expected = 6, actual = complexBinding.value)
    }
}
