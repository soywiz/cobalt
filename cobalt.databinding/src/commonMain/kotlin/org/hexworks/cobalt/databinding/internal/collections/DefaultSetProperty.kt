@file:Suppress("UNUSED_PARAMETER")

package org.hexworks.cobalt.databinding.internal.collections

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet
import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.api.collection.SetProperty
import org.hexworks.cobalt.databinding.internal.property.base.BaseProperty

@Suppress("UNCHECKED_CAST")
class DefaultSetProperty<T : Any>(
        initialValue: Set<T>,
        validator: Predicate<Set<T>> = { true }
) : BaseProperty<PersistentSet<T>>(
        initialValue = initialValue.toPersistentSet(),
        validator = validator), SetProperty<T> {

    override val size: Int
        get() = value.size

    override fun contains(element: T) = value.contains(element)

    override fun containsAll(elements: Collection<T>) = value.containsAll(elements)

    override fun isEmpty() = value.isEmpty()

    override fun iterator() = value.iterator()

    override fun add(element: T): PersistentSet<T> {
        return updateCurrentValue { it.add(element) }
    }

    override fun addAll(elements: Collection<T>): PersistentSet<T> {
        return updateCurrentValue { it.addAll(elements) }
    }

    override fun clear(): PersistentSet<T> {
        return updateCurrentValue { it.clear() }
    }

    override fun remove(element: T): PersistentSet<T> {
        return updateCurrentValue { it.remove(element) }
    }

    override fun removeAll(predicate: (T) -> Boolean): PersistentSet<T> {
        return updateCurrentValue { it.removeAll(predicate) }
    }

    override fun removeAll(elements: Collection<T>): PersistentSet<T> {
        return updateCurrentValue { it.removeAll(elements) }
    }

    override fun builder() = value.builder()

}
