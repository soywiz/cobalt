@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

operator fun ObservableValue<Int>.not(): Binding<Int> {
    return ComputedBinding(this) { -it }
}

infix operator fun ObservableValue<Int>.plus(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toInt() }
}

infix operator fun ObservableValue<Int>.minus(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toInt() }
}

infix operator fun ObservableValue<Int>.times(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toInt() }
}

infix operator fun ObservableValue<Int>.div(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toInt() }
}

infix fun ObservableValue<Int>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toInt() }
}

infix fun ObservableValue<Int>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toInt() }
}

infix fun ObservableValue<Int>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toInt() }
}

infix fun ObservableValue<Int>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toInt() }
}

infix fun ObservableValue<Int>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toInt() }
}

infix fun ObservableValue<Int>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toInt() }
}

fun ObservableValue<Int>.toStringBinding(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
