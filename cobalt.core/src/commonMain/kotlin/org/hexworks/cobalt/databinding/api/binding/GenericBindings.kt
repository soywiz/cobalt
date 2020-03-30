package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding

/**
 * Creates a [Binding] which will contain the transformed value of this [ObservableValue]
 * from its type [S] to a new type [T].
 */
fun <S : Any, T : Any> ObservableValue<S>.bindTransform(transformer: (S) -> T): Binding<T> {
    return ComputedBinding(this, transformer)
}