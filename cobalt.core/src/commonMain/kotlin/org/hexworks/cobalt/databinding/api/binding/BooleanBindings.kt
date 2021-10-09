package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

public fun ObservableValue<Boolean>.bindNot(): Binding<Boolean> {
    return ComputedBinding(this) { it.not() }
}

public infix fun ObservableValue<Boolean>.bindAndWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue && otherValue }
}

public infix fun ObservableValue<Boolean>.bindOrWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue || otherValue }
}

public infix fun ObservableValue<Boolean>.bindXorWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue xor otherValue }
}

public infix fun ObservableValue<Boolean>.bindEqualsWith(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

public fun ObservableValue<Boolean>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
