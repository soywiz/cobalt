package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

operator fun ObservableValue<Boolean>.not(): Binding<Boolean> {
    return ComputedBinding(this) { it.not() }
}

infix fun ObservableValue<Boolean>.and(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue && otherValue }
}

infix fun ObservableValue<Boolean>.or(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue || otherValue }
}

infix fun ObservableValue<Boolean>.xor(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue xor otherValue }
}

infix fun ObservableValue<Boolean>.isEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

infix fun ObservableValue<Boolean>.isNotEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue }
}

fun ObservableValue<Boolean>.toStringBinding(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
