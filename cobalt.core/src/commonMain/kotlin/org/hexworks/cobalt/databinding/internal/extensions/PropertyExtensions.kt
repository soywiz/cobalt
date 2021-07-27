package org.hexworks.cobalt.databinding.internal.extensions

import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.property.InternalProperty

internal fun <T : Any> Property<T>.asInternalProperty(): InternalProperty<T> {
    return this as InternalProperty<T>
}
