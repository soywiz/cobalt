package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.toConverter
import org.hexworks.cobalt.databinding.api.extension.toInternalProperty
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding
import org.hexworks.cobalt.databinding.internal.binding.UnidirectionalBinding

fun ObservableValue<String>.isEmpty(): Binding<Boolean> {
    val converter = { str: String -> str.isEmpty() }.toConverter()
    return UnidirectionalBinding(
            source = this,
            target = converter.convert(this.value).toInternalProperty(),
            converter = converter)
}

fun ObservableValue<String>.isNotEmpty(): Binding<Boolean> {
    return ComputedBinding(this) { it.isNotEmpty() }
}

infix operator fun ObservableValue<String>.plus(other: ObservableValue<String>): Binding<String> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue }
}

infix fun ObservableValue<String>.isEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

infix fun ObservableValue<String>.isNotEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue }
}

infix fun ObservableValue<String>.isEqualToIgnoreCase(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue.toLowerCase() == otherValue.toLowerCase() }
}

infix fun ObservableValue<String>.isNotEqualToIgnoreCase(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue.toLowerCase() != otherValue.toLowerCase() }
}

fun ObservableValue<String>.length(): Binding<Int> {
    return ComputedBinding(this) { it.length }
}
