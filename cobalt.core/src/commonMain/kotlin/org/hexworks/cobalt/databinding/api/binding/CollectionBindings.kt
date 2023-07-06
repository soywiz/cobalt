package org.hexworks.cobalt.databinding.api.binding

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.collection.ObservableListBinding
import org.hexworks.cobalt.databinding.api.collection.ObservableSet
import org.hexworks.cobalt.databinding.api.collection.ObservableSetBinding
import org.hexworks.cobalt.databinding.api.extension.ObservablePersistentCollection
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding
import org.hexworks.cobalt.databinding.internal.collections.ListBindingDecorator
import org.hexworks.cobalt.databinding.internal.collections.SetBindingDecorator

fun <T> ObservablePersistentCollection<T>.bindSize(): Binding<Int> {
    return ComputedBinding(this) { value ->
        value.size
    }
}

fun <T> ObservableList<ObservablePersistentCollection<T>>.bindFlatten(): ObservableListBinding<T> {
    return ListBindingDecorator(
        ComputedBinding(this) { value ->
            var result = persistentListOf<T>()
            value.forEach {
                result = result.addAll(it.value)
            }
            result
        }
    )
}

fun <T, R> ObservableList<ObservablePersistentCollection<T>>.bindFlatMap(
    converter: (T) -> R
): ObservableListBinding<R> {
    return ListBindingDecorator(
        ComputedBinding(this) { value ->
            var result = persistentListOf<R>()
            value.forEach {
                result = result.addAll(it.value.map(converter))
            }
            result
        }
    )
}

fun <T> ObservableSet<ObservablePersistentCollection<T>>.bindFlatten(): ObservableSetBinding<T> {
    return SetBindingDecorator(
        ComputedBinding(this) { value ->
            var result = persistentSetOf<T>()
            value.forEach {
                result = result.addAll(it.value)
            }
            result
        }
    )
}

fun <T, R> ObservableSet<ObservablePersistentCollection<T>>.bindFlatMap(
    converter: (T) -> R
): ObservableSetBinding<R> {
    return SetBindingDecorator(
        ComputedBinding(this) { value ->
            var result = persistentSetOf<R>()
            value.forEach {
                result = result.addAll(it.value.map(converter))
            }
            result
        }
    )
}

fun <T> ObservablePersistentCollection<T>.bindIsEmpty(): Binding<Boolean> {
    return ComputedBinding(this) { value ->
        value.isEmpty()
    }
}

infix fun <T> ObservablePersistentCollection<T>.bindContainsWith(
    other: ObservableValue<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.contains(otherValue)
    }
}

infix fun <T> ObservablePersistentCollection<T>.bindContainsAllWith(
    other: ObservablePersistentCollection<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.containsAll(otherValue)
    }
}

infix fun <T> ObservablePersistentCollection<T>.bindIndexOfWith(
    other: ObservableValue<T>
): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.indexOf(otherValue)
    }
}

infix fun <T> ObservablePersistentCollection<T>.bindLastIndexOfWith(
    other: ObservableValue<T>
): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.lastIndexOf(otherValue)
    }
}

infix fun <T> ObservablePersistentCollection<T>.bindIsEqualToWith(
    other: ObservablePersistentCollection<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

infix fun <T> ObservableValue<PersistentList<T>>.bindPlusWith(
    other: ObservableValue<PersistentList<T>>
): ObservableListBinding<T> {
    return ListBindingDecorator(
        ComputedDualBinding(this, other) { thisValue, otherValue ->
            thisValue.addAll(otherValue)
        }
    )
}

infix fun <T> ObservableValue<PersistentList<T>>.bindMinusWith(
    other: ObservableValue<PersistentList<T>>
): ObservableListBinding<T> {
    return ListBindingDecorator(
        ComputedDualBinding(this, other) { thisValue, otherValue ->
            thisValue.removeAll(otherValue)
        }
    )
}

infix fun <T> ObservableValue<PersistentSet<T>>.bindPlusWith(
    other: ObservableValue<PersistentSet<T>>
): ObservableSetBinding<T> {
    return SetBindingDecorator(
        ComputedDualBinding(this, other) { thisValue, otherValue ->
            thisValue.addAll(otherValue)
        }
    )
}

infix fun <T> ObservableValue<PersistentSet<T>>.bindMinusWith(
    other: ObservableValue<PersistentSet<T>>
): ObservableSetBinding<T> {
    return SetBindingDecorator(
        ComputedDualBinding(this, other) { thisValue, otherValue ->
            thisValue.removeAll(otherValue)
        }
    )
}
