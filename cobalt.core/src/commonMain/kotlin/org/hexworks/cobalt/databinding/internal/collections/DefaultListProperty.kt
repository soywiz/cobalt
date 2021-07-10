@file:Suppress("UNUSED_PARAMETER")

package org.hexworks.cobalt.databinding.internal.collections

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.collection.ListProperty
import org.hexworks.cobalt.databinding.api.event.*
import org.hexworks.cobalt.databinding.api.property.PropertyValidator
import org.hexworks.cobalt.databinding.api.value.ValueValidationFailedException
import org.hexworks.cobalt.databinding.internal.property.base.BaseProperty
import org.hexworks.cobalt.datatypes.Maybe
import kotlin.jvm.Synchronized

@Suppress("UNCHECKED_CAST")
class DefaultListProperty<T : Any>(
    initialValue: List<T>,
    validator: PropertyValidator<List<T>> = { _, _ -> true }
) : BaseProperty<PersistentList<T>>(
    initialValue = initialValue.toPersistentList(),
    validator = validator
), ListProperty<T> {

    override val size: Int
        get() = value.size

    override fun contains(element: T) = value.contains(element)

    override fun containsAll(elements: Collection<T>) = value.containsAll(elements)

    override fun get(index: Int) = value[index]

    override fun indexOf(element: T) = value.indexOf(element)

    override fun isEmpty() = value.isEmpty()

    override fun iterator() = value.iterator()

    override fun lastIndexOf(element: T) = value.lastIndexOf(element)

    override fun listIterator() = value.listIterator()

    override fun listIterator(index: Int) = value.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = value.subList(fromIndex, toIndex)

    override fun builder() = value.builder()

    // MUTATORS

    override fun add(element: T): PersistentList<T> {
        return updateCurrentValue(ListAdd(element)) { it.add(element) }
    }

    override fun add(index: Int, element: T): PersistentList<T> {
        return updateCurrentValue(ListAddAt(index, element)) { it.add(index, element) }
    }

    override fun set(index: Int, element: T): PersistentList<T> {
        return updateCurrentValue(ListSet(index, element)) { it.set(index, element) }
    }

    override fun remove(element: T): PersistentList<T> {
        return updateCurrentValue(ListRemove(element)) { it.remove(element) }
    }

    override fun removeAt(index: Int): PersistentList<T> {
        return updateCurrentValue(ListRemoveAt(index)) { it.removeAt(index) }
    }

    override fun addAll(elements: Collection<T>): PersistentList<T> {
        return updateCurrentValue(ListAddAll(elements)) { it.addAll(elements) }
    }

    override fun addAll(index: Int, c: Collection<T>): PersistentList<T> {
        return updateCurrentValue(ListAddAllAt(index, c)) { it.addAll(index, c) }
    }

    override fun removeAll(elements: Collection<T>): PersistentList<T> {
        return updateCurrentValue(ListRemoveAll(elements)) { it.removeAll(elements) }
    }

    override fun removeAll(predicate: (T) -> Boolean): PersistentList<T> {
        return updateCurrentValue(ListRemoveAllWhen(predicate)) { it.removeAll(predicate) }
    }

    override fun clear(): PersistentList<T> {
        return updateCurrentValue(ListClear) { it.clear() }
    }

}
