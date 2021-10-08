package org.hexworks.cobalt.databinding.internal.collections

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.hexworks.cobalt.databinding.api.binding.bindMap
import org.hexworks.cobalt.databinding.api.binding.bindPlusWith
import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.event.ListAdd
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.extension.toProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName")
open class DefaultListPropertyTest {

    val target = NUMBERS_1_TO_3.toProperty()

    @Test
    fun When_a_number_is_added_to_target_Then_its_value_changes() {
        target.add(4)

        assertEquals(NUMBERS_1_TO_4, target.value)
    }

    @Test
    fun When_target_changes_Then_its_change_is_emitted_as_an_event() {
        var newValue = listOf<Int>()

        target.onChange {
            newValue = it.newValue
        }

        target.add(4)

        assertEquals(NUMBERS_1_TO_4, newValue)
    }

    @Test
    fun When_a_hierarchical_list_binding_is_created_Then_the_value_is_correct() {

        val list0 = listOf(1).toProperty()
        val list1 = listOf(2).toProperty()
        val list2 = listOf(3).toProperty()

        val binding = (list0 bindPlusWith list1) bindPlusWith list2

        assertEquals(listOf(1, 2, 3), binding.value)

    }

    @Test
    fun When_the_lists_in_a_hierarchical_list_binding_are_modified_Then_the_value_stays_correct() {

        val list0 = listOf(1).toProperty()
        val list1 = listOf(3).toProperty()
        val list2 = listOf(5).toProperty()

        val binding = (list0 bindPlusWith list1) bindPlusWith list2

        list0.add(2)
        list1.add(4)
        list2.add(6)

        assertEquals(listOf(1, 2, 3, 4, 5, 6), binding.value)
    }

    @Test
    fun When_target_is_bound_to_other_Then_its_value_changes() {
        val other = NUMBERS_1_TO_4.toProperty()

        target.bind(other)

        assertEquals(NUMBERS_1_TO_4, target.value)
    }

    @Test
    fun When_a_list_transform_binding_is_created_Then_its_value_is_correct() {
        val prop = listOf(1, 2).toProperty()

        val binding = prop.bindMap {
            it.toString()
        }

        assertEquals(listOf("1", "2"), binding.value)
    }

    @Test
    fun Given_a_list_transform_binding_When_the_underlying_list_changes_Then_its_value_is_correct() {
        val prop = listOf(1, 2).toProperty()

        val binding = prop.bindMap {
            it.toString()
        }

        prop.add(3)

        assertEquals(listOf("1", "2", "3"), binding.value)
    }

    @Test
    fun Given_a_list_transform_binding_When_the_underlying_list_changes_Then_the_emitted_event_is_correct() {
        val prop = listOf(1, 2).toProperty()

        val binding = prop.bindMap {
            it.toString()
        }

        val changes = mutableListOf<ObservableValueChanged<PersistentList<String>>>()

        binding.onChange {
            changes.add(it)
        }

        prop.add(3)

        assertEquals(1, changes.size)

        val change = changes.first()

        assertEquals(
            expected = ObservableValueChanged(
                oldValue = persistentListOf("1", "2"),
                newValue = persistentListOf("1", "2", "3"),
                observableValue = change.observableValue,
                type = ListAdd(3),
                emitter = change.emitter,
                trace = change.trace
            ),
            actual = change
        )
    }

    @Test
    fun Given_a_hierarchical_list_transform_binding_When_the_underlying_list_changes_Then_its_value_is_correct() {
        val prop = listOf(1, 2).toProperty()

        val binding = prop.bindMap {
            it.toString()
        }

        val derived = binding bindPlusWith listOf("foo").toProperty()

        prop.add(3)

        assertEquals(listOf("1", "2", "3", "foo"), derived.value)
    }

    @Test
    fun Given_a_array_of_lists_to_bind_When_they_are_bound_Then_the_final_binding_is_correct() {

        val prop0: ObservableList<Int> = listOf(0).toProperty()
        val prop1: ObservableList<Int> = listOf(1).toProperty()
        val prop2: ObservableList<Int> = listOf(2).toProperty()
        val prop3: ObservableList<Int> = listOf(3).toProperty()
        val prop4: ObservableList<Int> = listOf(4).toProperty()

        val propsList = listOf(prop0, prop1, prop2, prop3, prop4)

        val binding = propsList.reduce { acc, observableList -> acc bindPlusWith observableList }

        assertEquals(persistentListOf(0, 1, 2, 3, 4), binding.value)
    }

    @Test
    fun Given_a_array_of_lists_to_bind_When_they_are_modified_Then_the_final_binding_is_correct() {

        val prop0: ObservableList<Int> = listOf(0).toProperty()
        val prop1: ObservableList<Int> = listOf(1).toProperty()
        val prop2: ObservableList<Int> = listOf(2).toProperty()
        val prop3: ObservableList<Int> = listOf(3).toProperty()
        val prop4: ObservableList<Int> = listOf(5).toProperty()

        val propsList = listOf(prop0, prop1, prop2, prop3, prop4)

        val binding = propsList.reduce { acc, observableList -> acc bindPlusWith observableList }

        prop3.add(4)

        assertEquals(persistentListOf(0, 1, 2, 3, 4, 5), binding.value)
    }

    @Test
    fun Given_a_map_transformation_When_an_element_is_set_Then_only_one_transformation_is_executed() {
        val prop: ObservableList<Int> = listOf(0, 1, 2).toProperty()

        var transformationCount = 0

        prop.bindMap {
            transformationCount++
            it + 1
        }

        transformationCount = 0

        prop.set(0, 5)

        assertEquals(1, transformationCount)
    }

    companion object {
        val NUMBERS_1_TO_3 = listOf(1, 2, 3)
        val NUMBERS_1_TO_4 = listOf(1, 2, 3, 4)
    }
}














