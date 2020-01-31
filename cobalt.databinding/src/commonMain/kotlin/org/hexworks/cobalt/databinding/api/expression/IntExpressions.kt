@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Int>.bindNegate(): Binding<Int> {
    return ComputedBinding(this) { -it }
}

infix fun ObservableValue<Int>.bindPlusWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindMinusWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindTimesWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindDivWith(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindEqualsWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindGreaterThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindLessThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindGreaterThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toInt() }
}

infix fun ObservableValue<Int>.bindLessThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toInt() }
}

fun ObservableValue<Int>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
