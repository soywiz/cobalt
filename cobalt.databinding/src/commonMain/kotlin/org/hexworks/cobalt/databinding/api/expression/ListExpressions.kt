package org.hexworks.cobalt.databinding.api.expression

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.extension.ObservablePersistentList
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun <T : Any> ObservablePersistentList<T>.combine(other: ObservablePersistentList<T>): Binding<PersistentList<T>> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.addAll(otherValue)
    }
}

fun <T : Any> ObservablePersistentList<T>.minus(other: ObservablePersistentList<T>): Binding<PersistentList<T>> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.removeAll(otherValue)
    }
}

fun <T : Any> ObservablePersistentList<T>.isEqualTo(other: ObservablePersistentList<T>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}