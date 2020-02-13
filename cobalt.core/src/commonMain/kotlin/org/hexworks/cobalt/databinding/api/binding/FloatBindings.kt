@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Float>.bindNegate(): Binding<Float> {
    return ComputedBinding(this) { -it }
}

infix fun ObservableValue<Float>.bindPlusWith(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindMinusWith(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindTimesWith(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindDivWith(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindEqualsWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindGreaterThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindLessThanWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindGreaterThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toFloat() }
}

infix fun ObservableValue<Float>.bindLessThanOrEqualToWith(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toFloat() }
}

fun ObservableValue<Float>.bindToString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
