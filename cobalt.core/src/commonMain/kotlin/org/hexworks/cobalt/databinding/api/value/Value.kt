package org.hexworks.cobalt.databinding.api.value

import org.hexworks.cobalt.databinding.api.property.Property

/**
 * [Value] is an abstraction which can be used to wrap a [value].
 * It serves as the basis for [ObservableValue] and [WritableValue]
 * which together form the [Property] abstraction.
 */
public interface Value<out T> {

    val value: T

    companion object
}
