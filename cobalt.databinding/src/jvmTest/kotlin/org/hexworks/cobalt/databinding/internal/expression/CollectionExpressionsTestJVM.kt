package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.expression.bindIsEqualToWith
import org.hexworks.cobalt.databinding.api.expression.bindMinusWith
import org.hexworks.cobalt.databinding.api.expression.bindPlusWith
import org.hexworks.cobalt.databinding.api.extension.toProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName", "TestFunctionName")
class CollectionExpressionsTestJVM {

    private val prop1To3 = NUMBERS_1_TO_3.toProperty()
    private val prop4To6 = NUMBERS_4_TO_6.toProperty()
    private val prop7To9 = NUMBERS_7_TO_9.toProperty()

    @Test
    fun When_list_properties_are_combined_Then_the_binding_value_is_the_combined_list() {

        val binding = prop1To3 bindPlusWith prop4To6

        assertEquals(
                expected = NUMBERS_1_TO_3 + NUMBERS_4_TO_6,
                actual = binding.value.toList())
    }

    @Test
    fun When_list_property_and_binding_are_combined_Then_the_binding_value_is_the_combined_list() {

        val binding = prop1To3 bindPlusWith prop4To6
        val derivedBinding = binding bindPlusWith prop7To9

        assertEquals(
                expected = NUMBERS_1_TO_3 + NUMBERS_4_TO_6 + NUMBERS_7_TO_9,
                actual = derivedBinding.value.toList())
    }

    @Test
    fun When_list_properties_are_subtracted_Then_the_binding_value_is_the_subtracted_list() {

        val binding1To6 = prop1To3 bindPlusWith prop4To6

        val subtracted = binding1To6 bindMinusWith prop4To6

        assertEquals(NUMBERS_1_TO_3, subtracted.value)
    }

    @Test
    fun When_list_property_and_binding_are_subtracted_Then_the_binding_value_is_the_subtracted_list() {

        val binding = prop1To3 bindPlusWith prop4To6
        val derivedBinding = binding bindPlusWith prop7To9

        val subtracted = derivedBinding bindMinusWith prop7To9

        assertEquals(NUMBERS_1_TO_3 + NUMBERS_4_TO_6, subtracted.value.toList())
    }

    @Test
    fun When_properties_are_equal_Then_derived_is_equal_binding_is_true() {

        val binding = prop1To3 bindIsEqualToWith prop1To3

        assertEquals(true, binding.value)
    }

    @Test
    fun When_properties_are_not_equal_Then_derived_is_equal_binding_is_false() {

        val binding = prop1To3 bindIsEqualToWith prop4To6

        assertEquals(false, binding.value)
    }

    companion object {

        val NUMBERS_1_TO_3: Collection<Int> = listOf(1, 2, 3)
        val NUMBERS_4_TO_6: Collection<Int> = listOf(4, 5, 6)
        val NUMBERS_7_TO_9: Collection<Int> = listOf(7, 8, 9)
    }
}
