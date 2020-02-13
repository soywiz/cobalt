package org.hexworks.cobalt.databinding.api.binding

import kotlinx.collections.immutable.PersistentCollection
import org.hexworks.cobalt.databinding.api.extension.ObservablePersistentCollection
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun <T : Any> ObservablePersistentCollection<T>.bindSize(): Binding<Int> {
    return ComputedBinding(this) { value ->
        value.size
    }
}

fun <T : Any> ObservablePersistentCollection<T>.bindIsEmpty(): Binding<Boolean> {
    return ComputedBinding(this) { value ->
        value.isEmpty()
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindContainsWith(
        other: ObservableValue<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.contains(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindContainsAllWith(
        other: ObservablePersistentCollection<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.containsAll(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindIndexOfWith(
        other: ObservableValue<T>
): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.indexOf(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindLastIndexOfWith(
        other: ObservableValue<T>
): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.lastIndexOf(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindPlusWith(
        other: ObservablePersistentCollection<T>
): Binding<PersistentCollection<T>> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.addAll(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindMinusWith(
        other: ObservablePersistentCollection<T>
): Binding<PersistentCollection<T>> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.removeAll(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindIsEqualToWith(
        other: ObservablePersistentCollection<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}