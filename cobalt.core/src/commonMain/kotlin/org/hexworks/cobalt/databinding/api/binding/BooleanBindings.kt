package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Boolean>.bindNot(): Binding<Boolean> {
    return ComputedBinding(this) { it.not() }
}

infix fun ObservableValue<Boolean>.bindAndWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue && otherValue }
}

infix fun ObservableValue<Boolean>.bindOrWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue || otherValue }
}

infix fun ObservableValue<Boolean>.bindXorWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue xor otherValue }
}

infix fun ObservableValue<Boolean>.bindEqualsWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

fun ObservableValue<Boolean>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
