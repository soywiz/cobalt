@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Double>.bindNegate(): Binding<Double> {
    return ComputedBinding(this) { -it }
}

infix fun ObservableValue<Double>.bindPlusWith(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindMinusWith(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindTimesWith(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindDivWith(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindEqualsWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindGreaterThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindLessThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindGreaterThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toDouble() }
}

infix fun ObservableValue<Double>.bindLessThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toDouble() }
}

fun ObservableValue<Double>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
