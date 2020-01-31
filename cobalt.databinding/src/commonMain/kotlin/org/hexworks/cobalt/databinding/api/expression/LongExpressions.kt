@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

operator fun ObservableValue<Long>.not(): Binding<Long> {
    return ComputedBinding(this) { -it }
}

infix operator fun ObservableValue<Long>.plus(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toLong() }
}

infix operator fun ObservableValue<Long>.minus(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toLong() }
}

infix operator fun ObservableValue<Long>.times(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toLong() }
}

infix operator fun ObservableValue<Long>.div(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toLong() }
}

infix fun ObservableValue<Long>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toLong() }
}

infix fun ObservableValue<Long>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toLong() }
}

infix fun ObservableValue<Long>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toLong() }
}

infix fun ObservableValue<Long>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toLong() }
}

infix fun ObservableValue<Long>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toLong() }
}

infix fun ObservableValue<Long>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toLong() }
}

fun ObservableValue<Long>.toStringBinding(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
