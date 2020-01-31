@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

operator fun ObservableValue<Float>.not(): Binding<Float> {
    return ComputedBinding(this) { -it }
}

infix operator fun ObservableValue<Float>.plus(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toFloat() }
}

infix operator fun ObservableValue<Float>.minus(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toFloat() }
}

infix operator fun ObservableValue<Float>.times(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toFloat() }
}

infix operator fun ObservableValue<Float>.div(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toFloat() }
}

infix fun ObservableValue<Float>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toFloat() }
}

infix fun ObservableValue<Float>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toFloat() }
}

infix fun ObservableValue<Float>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toFloat() }
}

infix fun ObservableValue<Float>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toFloat() }
}

infix fun ObservableValue<Float>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toFloat() }
}

infix fun ObservableValue<Float>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toFloat() }
}

fun ObservableValue<Float>.toStringBinding(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
