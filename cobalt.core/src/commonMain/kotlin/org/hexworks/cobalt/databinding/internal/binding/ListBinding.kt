package org.hexworks.cobalt.databinding.internal.binding

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.collection.ObservableList
import org.hexworks.cobalt.databinding.api.event.*
import org.hexworks.cobalt.databinding.api.extension.map
import org.hexworks.cobalt.databinding.api.extension.toInternalProperty
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.ValueValidationResult
import org.hexworks.cobalt.databinding.internal.exception.CircularBindingException

/**
 * A [ListBinding] creates a [Binding] using an [ObservableValue]. The [Binding] will get
 * updated whenever the [ObservableValue] changes using [converter] to compute the new value of this [Binding].
 */
@Suppress("UNCHECKED_CAST")
class ListBinding<S : Any, T : Any>(
    source: ObservableList<S>,
    converter: (S) -> T
) : BaseBinding<PersistentList<S>, PersistentList<T>>(
    source = source,
    target = source.value.map { converter(it) }.toInternalProperty(),
    subscriptions = mutableListOf()
) {

    init {
        subscriptions.add(source.onChange { event ->
            if (event.trace.any { it.emitter == this }) {
                throw CircularBindingException(
                    "Circular binding detected with trace ${event.trace.joinToString()} for property $this. Loop was prevented."
                )
            }
            val type = event.type
            val oldValue = target.value
            val maybeNewValue: ValueValidationResult<PersistentList<T>> = target.transformValue {
                when(type) {
                    is ScalarChange -> {
                        event.newValue.map { item -> converter(item) }
                    }
                    is ListChange -> {
                        when(type) {
                            is ListAdd<*> -> oldValue.add(converter(type.element as S))
                            is ListAddAt<*> -> oldValue.add(type.index, converter(type.element as S))
                            is ListRemove<*> -> oldValue.remove(converter(type.element as S))
                            is ListRemoveAt -> oldValue.removeAt(type.index)
                            is ListSet<*> -> oldValue.set(type.index, converter(type.element as S))
                            is ListAddAll<*> -> oldValue.addAll(type.elements.map { converter(it as S) })
                            is ListAddAllAt<*> -> oldValue.addAll(type.index, type.c.map { converter(it as S) })
                            is ListRemoveAll<*> -> oldValue.removeAll(type.elements.map { converter(it as S) })
                            is ListRemoveAllWhen<*> -> oldValue.removeAll {
                                (type.predicate as (T) -> Boolean)(it)
                            }
                            ListClear -> oldValue.clear()
                        }
                    }
                }
            }
            if(maybeNewValue.successful && oldValue != maybeNewValue.value) {
                Cobalt.eventbus.publish(
                    event = ObservableValueChanged(
                        oldValue = oldValue,
                        newValue = maybeNewValue.value,
                        observableValue = this,
                        emitter = this,
                        type = event.type,
                        trace = listOf(event) + event.trace
                    ),
                    eventScope = propertyScope
                )
            }
        })
    }

    override fun toString() = "ListBinding(source=$source, target=$target)"
}
