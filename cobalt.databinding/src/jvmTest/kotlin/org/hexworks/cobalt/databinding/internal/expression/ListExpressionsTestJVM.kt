package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.collection.toProperty
import org.hexworks.cobalt.databinding.api.expression.combine
import org.hexworks.cobalt.databinding.api.expression.isEqualTo
import org.hexworks.cobalt.databinding.api.expression.minus
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName", "TestFunctionName")
class ListExpressionsTestJVM {

    private val prop1To3 = NUMBERS_1_TO_3.toProperty()
    private val prop4To6 = NUMBERS_4_TO_6.toProperty()
    private val prop7To9 = NUMBERS_7_TO_9.toProperty()

    @Test
    fun When_list_properties_are_combined_Then_the_binding_value_is_the_combined_list() {

        val binding = prop1To3.combine(prop4To6)

        assertEquals(NUMBERS_1_TO_3.plus(NUMBERS_4_TO_6), binding.value)
    }

    @Test
    fun When_list_property_and_binding_are_combined_Then_the_binding_value_is_the_combined_list() {

        val binding = prop1To3.combine(prop4To6)
        val derivedBinding = binding.combine(prop7To9)

        assertEquals(NUMBERS_1_TO_3 + NUMBERS_4_TO_6 + NUMBERS_7_TO_9, derivedBinding.value)
    }

    @Test
    fun When_list_properties_are_subtracted_Then_the_binding_value_is_the_subtracted_list() {

        val binding1To6 = prop1To3.combine(prop4To6)

        val subtracted = binding1To6.minus(prop4To6)

        assertEquals(NUMBERS_1_TO_3, subtracted.value)
    }

    @Test
    fun When_list_property_and_binding_are_subtracted_Then_the_binding_value_is_the_subtracted_list() {

        val binding = prop1To3.combine(prop4To6)
        val derivedBinding = binding.combine(prop7To9)

        val subtracted = derivedBinding.minus(prop7To9)

        assertEquals(NUMBERS_1_TO_3 + NUMBERS_4_TO_6, subtracted.value)
    }

    @Test
    fun When_properties_are_equal_Then_derived_is_equal_binding_is_true() {

        val binding = prop1To3.isEqualTo(prop1To3)

        assertEquals(true, binding.value)
    }

    @Test
    fun When_properties_are_not_equal_Then_derived_is_equal_binding_is_false() {

        val binding = prop1To3.isEqualTo(prop4To6)

        assertEquals(false, binding.value)
    }

    companion object {

        val NUMBERS_1_TO_3 = listOf(1, 2, 3)
        val NUMBERS_4_TO_6 = listOf(4, 5, 6)
        val NUMBERS_7_TO_9 = listOf(7, 8, 9)
    }
}
