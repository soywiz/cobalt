@file:Suppress("UNUSED_PARAMETER")

package org.hexworks.cobalt.databinding.internal.collections

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.api.collection.ListProperty
import org.hexworks.cobalt.databinding.internal.property.base.BaseProperty

@Suppress("UNCHECKED_CAST")
class DefaultListProperty<T : Any>(
        initialValue: List<T>,
        validator: Predicate<List<T>> = { true }
) : BaseProperty<PersistentList<T>>(
        initialValue = initialValue.toPersistentList(),
        validator = validator), ListProperty<T> {

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


    override fun add(element: T): PersistentList<T> {
        return updateCurrentValue { it.add(element) }
    }

    override fun remove(element: T): PersistentList<T> {
        return updateCurrentValue { it.remove(element) }
    }

    override fun addAll(elements: Collection<T>): PersistentList<T> {
        return updateCurrentValue { it.addAll(elements) }
    }

    override fun addAll(index: Int, c: Collection<T>): PersistentList<T> {
        return updateCurrentValue { it.addAll(index, c) }
    }

    override fun removeAll(elements: Collection<T>): PersistentList<T> {
        return updateCurrentValue { it.removeAll(elements) }
    }

    override fun removeAll(predicate: (T) -> Boolean): PersistentList<T> {
        return updateCurrentValue { it.removeAll(predicate) }
    }

    override fun clear(): PersistentList<T> {
        return updateCurrentValue { it.clear() }
    }

    override fun set(index: Int, element: T): PersistentList<T> {
        return updateCurrentValue { it.set(index, element) }
    }

    override fun add(index: Int, element: T): PersistentList<T> {
        return updateCurrentValue { it.add(index, element) }
    }

    override fun removeAt(index: Int): PersistentList<T> {
        return updateCurrentValue { it.removeAt(index) }
    }

    override fun builder() = value.builder()

}
