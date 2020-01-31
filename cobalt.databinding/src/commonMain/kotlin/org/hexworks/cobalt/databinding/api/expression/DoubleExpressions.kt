@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

operator fun ObservableValue<Double>.not(): Binding<Double> {
    return ComputedBinding(this) { -it }
}

infix operator fun ObservableValue<Double>.plus(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toDouble() }
}

infix operator fun ObservableValue<Double>.minus(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toDouble() }
}

infix operator fun ObservableValue<Double>.times(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toDouble() }
}

infix operator fun ObservableValue<Double>.div(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toDouble() }
}

infix fun ObservableValue<Double>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toDouble() }
}

infix fun ObservableValue<Double>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toDouble() }
}


infix fun ObservableValue<Double>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toDouble() }
}

infix fun ObservableValue<Double>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toDouble() }
}

infix fun ObservableValue<Double>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toDouble() }
}

infix fun ObservableValue<Double>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toDouble() }
}

fun ObservableValue<Double>.toStringBinding(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
