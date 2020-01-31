package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding

fun <S : Any, T : Any> ObservableValue<S>.bindConvert(converter: (S) -> T): Binding<T> {
    return ComputedBinding(this) { converter(it) }
}