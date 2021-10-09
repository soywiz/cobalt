@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

public fun ObservableValue<Int>.bindNegate(): Binding<Int> {
    return ComputedBinding(this) { -it }
}

public infix fun ObservableValue<Int>.bindPlusWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindMinusWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindTimesWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindDivWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindEqualsWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindGreaterThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindLessThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindGreaterThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toInt() }
}

public infix fun ObservableValue<Int>.bindLessThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toInt() }
}

public fun ObservableValue<Int>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
