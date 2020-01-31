@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Long>.bindNegate(): Binding<Long> {
    return ComputedBinding(this) { -it }
}

infix fun ObservableValue<Long>.bindPlusWith(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindMinusWith(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindTimesWith(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindDivWith(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindEqualsWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindGreaterThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindLessThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindGreaterThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toLong() }
}

infix fun ObservableValue<Long>.bindLessThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toLong() }
}

fun ObservableValue<Long>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
