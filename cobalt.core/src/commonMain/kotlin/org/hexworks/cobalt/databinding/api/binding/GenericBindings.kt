package org.hexworks.cobalt.databinding.api.binding

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.collection.ObservableListBinding
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ListBinding
import org.hexworks.cobalt.databinding.internal.collections.ListBindingDecorator

/**
 * Creates a [Binding] which will contain the transformed value of this [ObservableValue]
 * from its type [S] to a new type [T].
 */
fun <S : Any, T : Any> ObservableValue<S>.bindTransform(transformer: (S) -> T): Binding<T> {
    return ComputedBinding(this, transformer)
}

/**
 * Creates a [Binding] which will contain the transformed value of this [ObservableValue]
 * from its type [S] to a new type [T].
 */
fun <S : Any, T : Any> ObservableList<S>.bindListTransform(transformer: (S) -> T): ObservableListBinding<T> {
    return ListBindingDecorator(ListBinding(this, transformer))
}