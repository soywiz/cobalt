package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.collection.ObservableListBinding
import org.hexworks.cobalt.databinding.api.collection.ObservableSet
import org.hexworks.cobalt.databinding.api.collection.ObservableSetBinding
import org.hexworks.cobalt.databinding.api.extension.ObservablePersistentCollection
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding
import org.hexworks.cobalt.databinding.internal.collections.ListBindingDecorator
import org.hexworks.cobalt.databinding.internal.collections.SetBindingDecorator

fun <T : Any> ObservablePersistentCollection<T>.bindSize(): Binding<Int> {
    return ComputedBinding(this) { value ->
        value.size
    }
}

fun <T : Any> ObservablePersistentCollection<T>.bindIsEmpty(): Binding<Boolean> {
    return ComputedBinding(this) { value ->
        value.isEmpty()
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindContainsWith(
    other: ObservableValue<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.contains(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindContainsAllWith(
    other: ObservablePersistentCollection<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.containsAll(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindIndexOfWith(
    other: ObservableValue<T>
): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.indexOf(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindLastIndexOfWith(
    other: ObservableValue<T>
): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.lastIndexOf(otherValue)
    }
}

infix fun <T : Any> ObservablePersistentCollection<T>.bindIsEqualToWith(
    other: ObservablePersistentCollection<T>
): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

infix fun <T : Any> ObservableList<T>.bindPlusWith(
    other: ObservableList<T>
): ObservableList<T> {
    return ListBindingDecorator(ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.addAll(otherValue)
    })
}

infix fun <T : Any> ObservableList<T>.bindMinusWith(
    other: ObservableList<T>
): ObservableListBinding<T> {
    return ListBindingDecorator(ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.removeAll(otherValue)
    })
}

infix fun <T : Any> ObservableSet<T>.bindPlusWith(
    other: ObservableSet<T>
): ObservableSetBinding<T> {
    return SetBindingDecorator(ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.addAll(otherValue)
    })
}

infix fun <T : Any> ObservableSet<T>.bindMinusWith(
    other: ObservableSet<T>
): ObservableSet<T> {
    return SetBindingDecorator(ComputedDualBinding(this, other) { thisValue, otherValue ->
        thisValue.removeAll(otherValue)
    })
}
