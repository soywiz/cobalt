package org.hexworks.cobalt.databinding.api.expression

import kotlinx.collections.immutable.PersistentCollection
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.extension.ObservablePersistentCollection
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

infix fun <T : Any> ObservablePersistentCollection<T>.bindContainsWith(
        other: ObservableValue<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.contains(otherValue)
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