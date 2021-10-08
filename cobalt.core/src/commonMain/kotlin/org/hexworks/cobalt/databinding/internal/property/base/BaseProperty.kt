package org.hexworks.cobalt.databinding.internal.property.base

import org.hexworks.cobalt.core.extensions.abbreviate
import org.hexworks.cobalt.core.internal.toAtom
import org.hexworks.cobalt.core.platform.factory.UUIDFactory
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IdentityConverter
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.converter.toConverter
import org.hexworks.cobalt.databinding.api.event.ChangeType
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.event.ScalarChange
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.property.PropertyValidator
import org.hexworks.cobalt.databinding.api.value.*
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalBinding
import org.hexworks.cobalt.databinding.internal.binding.UnidirectionalBinding
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.internal.exception.CircularBindingException
import org.hexworks.cobalt.databinding.internal.property.InternalProperty
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.simpleSubscribeTo
import org.hexworks.cobalt.logging.api.LoggerFactory
import kotlin.jvm.Synchronized

abstract class BaseProperty<T>(
    initialValue: T,
    override val name: String,
    private val validator: PropertyValidator<T> = { _, _ -> true }
) : InternalProperty<T> {

    override var value: T
        get() = backend.get()
        set(value) {
            updateCurrentValue { value }
        }

    final override val id = UUIDFactory.randomUUID()
    final override val logger = LoggerFactory.getLogger(this::class)
    final override val propertyScope = PropertyScope(id)

    private val backend = initialValue.toAtom()
    private val identityConverter = IdentityConverter<T>()

    override fun updateValue(newValue: T): ValueValidationResult<T> {
        return transformValue { newValue }
    }

    @Suppress("UNCHECKED_CAST")
    override fun transformValue(transformer: (oldValue: T) -> T): ValueValidationResult<T> {
        return try {
            ValueValidationSuccessful(updateCurrentValue(fn = transformer))
        } catch (e: ValueValidationFailedException) {
            ValueValidationFailed(e.newValue as T, e)
        }
    }


    override fun onChange(fn: (ObservableValueChanged<T>) -> Unit): Subscription {
        logger.debug { "Subscribing to changes to property: $this." }
        return Cobalt.eventbus.simpleSubscribeTo<ObservableValueChanged<T>>(propertyScope) {
            fn(it)
        }
    }

    override fun bind(other: Property<T>, updateWhenBound: Boolean): Binding<T> {
        return bind(
            other = other,
            updateWhenBound = updateWhenBound,
            converter = identityConverter
        )
    }

    override fun <S> bind(
        other: Property<S>,
        updateWhenBound: Boolean,
        converter: IsomorphicConverter<S, T>
    ): Binding<T> {
        logger.debug { "Binding property $this to other property $other." }
        checkSelfBinding(other)
        other as? InternalProperty<S> ?: error("Can only bind Properties which implement InternalProperty.")
        if (updateWhenBound) {
            updateCurrentValue { converter.convert(other.value) }
        }
        return BidirectionalBinding(
            source = other,
            target = this,
            converter = converter
        )
    }

    override fun updateFrom(
        observable: ObservableValue<T>,
        updateWhenBound: Boolean
    ): Binding<T> {
        return updateFrom(observable, updateWhenBound) { it }
    }

    override fun <S> updateFrom(
        observable: ObservableValue<S>,
        updateWhenBound: Boolean,
        converter: (S) -> T
    ): Binding<T> {
        logger.debug {
            "Starting to update property $this from $observable."
        }
        checkSelfBinding(observable)
        if (updateWhenBound) {
            updateCurrentValue { converter(observable.value) }
        }
        return UnidirectionalBinding(observable, this, converter.toConverter())
    }

    override fun updateWithEvent(
        oldValue: T,
        newValue: T,
        event: ObservableValueChanged<Any?>
    ): Boolean {

        logger.debug {
            "Trying to update $this using event $event with new value $newValue."
        }
        return try {
            // this trick enables the whole system not to crash if there is a circular dependency
            if (event.trace.any { it.emitter == this }) {
                throw CircularBindingException(
                    "Circular binding detected with trace ${event.trace.joinToString()} for property $this. Loop was prevented."
                )
            } else {
                var changed = false
                var eventToSend: ObservableValueChanged<T>? = null
                backend.transform {
                    if (validator(oldValue, newValue).not()) {
                        throw ValueValidationFailedException(newValue, "The given value '$newValue' is invalid.")
                    }
                    if (oldValue != newValue) {
                        changed = true
                        eventToSend = ObservableValueChanged(
                            oldValue = oldValue,
                            newValue = newValue,
                            observableValue = this,
                            emitter = this,
                            trace = listOf(event) + event.trace,
                            type = event.type
                        )
                    }
                    newValue
                }
                eventToSend?.let {
                    logger.debug {
                        "Old value $oldValue of $this differs from new value $newValue, firing change event."
                    }
                    Cobalt.eventbus.publish(
                        event = it,
                        eventScope = propertyScope
                    )
                }
                changed
            }
        } catch (e: CircularBindingException) {
            logger.warn("Bound Property was not updated due to circular dependency: ${e.message}")
            false
        }
    }

    // This won't lead to a deadlock in case we try to set the value twice in case of a
    // circular dependency, because we already have the monitor.
    @Synchronized
    protected open fun updateCurrentValue(type: ChangeType = ScalarChange, fn: (T) -> T): T {
        var eventToSend: ObservableValueChanged<T>? = null
        backend.transform { oldValue ->
            val newValue = fn(oldValue)
            if (validator(oldValue, newValue).not()) {
                throw ValueValidationFailedException(newValue, "The given value $newValue is invalid.")
            }
            if (type != ScalarChange || oldValue != newValue) {
                eventToSend = ObservableValueChanged(
                    oldValue = oldValue,
                    newValue = newValue,
                    type = type,
                    observableValue = this,
                    emitter = this
                )
            }
            newValue
        }
        eventToSend?.let {
            logger.debug {
                "Old value ${it.oldValue} of $this differs from new value ${it.newValue}, firing change event."
            }
            Cobalt.eventbus.publish(
                event = it,
                eventScope = propertyScope
            )
        }
        return backend.get()
    }

    private fun checkSelfBinding(other: ObservableValue<*>) {
        require(this !== other) {
            "Can't bind a property to itself."
        }
    }

    override fun toString() = "${name}(id=${id.abbreviate()}, value=$value)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BaseProperty<*>

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
