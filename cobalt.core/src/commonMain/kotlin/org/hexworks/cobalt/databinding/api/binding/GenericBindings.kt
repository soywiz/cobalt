package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.collection.ObservableListBinding
import org.hexworks.cobalt.databinding.api.collection.ObservableSet
import org.hexworks.cobalt.databinding.api.collection.ObservableSetBinding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ListBinding
import org.hexworks.cobalt.databinding.internal.binding.SetBinding
import org.hexworks.cobalt.databinding.internal.collections.ListBindingDecorator
import org.hexworks.cobalt.databinding.internal.collections.SetBindingDecorator

/**
 * Creates a [Binding] which will contain the transformed value of this [ObservableValue]
 * from its type [S] to a new type [T].
 */
public fun <S, T> ObservableValue<S>.bindTransform(transformer: (S) -> T): Binding<T> {
    return ComputedBinding(this, transformer)
}

/**
 * Creates a [Binding] which will contain the mapped values of this [ObservableList]
 * from its type [S] to a new type [T].
 */
public fun <S, T> ObservableList<S>.bindMap(transformer: (S) -> T): ObservableListBinding<T> {
    return ListBindingDecorator(ListBinding(this, transformer))
}

/**
 * Creates a [Binding] which will contain the mapped values of this [ObservableList]
 * from its type [S] to a new type [T].
 */
public fun <S, T> ObservableSet<S>.bindMap(transformer: (S) -> T): ObservableSetBinding<T> {
    return SetBindingDecorator(SetBinding(this, transformer))
}
