package org.hexworks.cobalt.databinding.internal.collections

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.hexworks.cobalt.databinding.api.binding.bindMap
import org.hexworks.cobalt.databinding.api.binding.bindPlusWith
import org.hexworks.cobalt.databinding.api.collection.ListProperty
import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.event.ListAdd
import org.hexworks.cobalt.databinding.api.event.ListPropertyChange
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.api.property.Property
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

@Suppress("TestFunctionName")
class DefaultPropertyListPropertyTest {

    val target: ListProperty<Property<Int>> = NUMBERS_1_TO_3.toProperty()

    @Test
    fun When_a_number_is_added_to_target_Then_its_value_changes() {
        target.add(4.toProperty())

        assertEquals(NUMBERS_1_TO_4, target.value.map { it.value })
    }

    @Test
    fun When_target_changes_Then_its_change_is_emitted_as_an_event() {
        var newValue = listOf<Property<Int>>()

        target.onChange {
            newValue = it.newValue
        }

        target.add(4.toProperty())

        assertEquals(NUMBERS_1_TO_4.map { it }, newValue.map { it.value })
    }

    @Test
    fun When_a_hierarchical_list_binding_is_created_Then_the_value_is_correct() {

        val list0 = listOf(1.toProperty()).toProperty()
        val list1 = listOf(2.toProperty()).toProperty()
        val list2 = listOf(3.toProperty()).toProperty()

        val binding = (list0 bindPlusWith list1) bindPlusWith list2

        assertEquals(persistentListOf(1, 2, 3), binding.value.map { it.value })

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
        val other = NUMBERS_1_TO_4.map { it.toProperty() }.toProperty()

        target.bind(other)

        assertEquals(other.value, target.value)
    }

    @Test
    fun When_a_list_transform_binding_is_created_Then_its_value_is_correct() {
        val prop = listOf(1.toProperty(), 2.toProperty()).toProperty()

        val binding = prop.bindMap {
            it.value.toString()
        }

        assertEquals(listOf("1", "2"), binding.value)
    }

    @Test
    fun Given_a_list_transform_binding_When_the_underlying_list_changes_Then_its_value_is_correct() {
        val prop = listOf(1.toProperty(), 2.toProperty()).toProperty()

        val binding = prop.bindMap {
            it.value.toString()
        }

        prop.add(3.toProperty())

        assertEquals(listOf("1", "2", "3"), binding.value)
    }

    @Test
    fun Given_a_list_transform_binding_When_the_underlying_list_changes_Then_the_emitted_event_is_correct() {
        val prop = listOf(1.toProperty(), 2.toProperty()).toProperty()

        val binding = prop.bindMap {
            it.value.toString()
        }

        val listChanges = mutableListOf<ObservableValueChanged<PersistentList<Property<Int>>>>()
        val changes = mutableListOf<ObservableValueChanged<PersistentList<String>>>()

        prop.onChange {
            listChanges.add(it)
        }

        binding.onChange {
            changes.add(it)
        }

        prop.add(3.toProperty())

        assertEquals(1, changes.size)

        val change = changes.first()

        assertEquals(
            expected = ObservableValueChanged(
                oldValue = persistentListOf("1", "2"),
                newValue = persistentListOf("1", "2", "3"),
                observableValue = change.observableValue,
                type = listChanges.first().type,
                emitter = change.emitter,
                trace = change.trace
            ),
            actual = change
        )
    }

    @Test
    fun Given_a_hierarchical_list_transform_binding_When_the_underlying_list_changes_Then_its_value_is_correct() {
        val prop = listOf(1.toProperty(), 2.toProperty()).toProperty()

        val binding = prop.bindMap {
            it.value.toString()
        }

        val derived = binding bindPlusWith listOf("foo").toProperty()

        prop.add(3.toProperty())

        assertEquals(listOf("1", "2", "3", "foo"), derived.value)
    }

    @Test
    fun Given_a_map_transformation_When_an_element_is_set_Then_only_one_transformation_is_executed() {
        val prop = listOf(0.toProperty(), 1.toProperty(), 2.toProperty()).toProperty()

        var transformationCount = 0

        prop.bindMap {
            transformationCount++
            it.value + 1
        }

        transformationCount = 0

        prop.set(0, 5.toProperty())

        assertEquals(1, transformationCount)
    }

    @Test
    fun Given_a_property_list_When_a_property_changes_Then_the_proper_event_is_fired() {
        val prop = target[0]
        var change: ObservableValueChanged<PersistentList<Property<Int>>>? = null
        var innerChange: ObservableValueChanged<Int>? = null
        prop.onChange {
            innerChange = it
        }
        target.onChange {
            change = it
        }
        prop.value = 0

        assertTrue {
            change?.type is ListPropertyChange<*>
        }
        assertEquals(innerChange!!, (change!!.type as ListPropertyChange<*>).changeEvent)
    }

    @Test
    fun Given_a_property_list_When_a_property_is_added_and_changes_Then_the_proper_event_is_fired() {
        val prop = 4.toProperty()
        var change: ObservableValueChanged<PersistentList<Property<Int>>>? = null
        var innerChange: ObservableValueChanged<Int>? = null
        prop.onChange {
            innerChange = it
        }
        target.onChange {
            change = it
        }
        target.add(prop)
        prop.value = 0

        assertTrue {
            change?.type is ListPropertyChange<*>
        }
        assertEquals(innerChange!!, (change!!.type as ListPropertyChange<*>).changeEvent)
    }

    @Test
    fun Given_a_property_list_When_a_property_is_added_and_removed_and_changes_Then_there_is_no_event_fired() {
        val prop = 4.toProperty()
        var change: ObservableValueChanged<PersistentList<Property<Int>>>? = null
        target.add(prop)
        target.remove(prop)
        target.onChange {
            change = it
        }
        prop.value = 0

        assertEquals(null, change)
    }

    companion object {
        val NUMBERS_1_TO_3 = listOf(1.toProperty(), 2.toProperty(), 3.toProperty())
        val NUMBERS_1_TO_4 = listOf(1, 2, 3, 4)
    }

}
