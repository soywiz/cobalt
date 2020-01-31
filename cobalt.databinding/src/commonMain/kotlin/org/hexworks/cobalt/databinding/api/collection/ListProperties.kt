package org.hexworks.cobalt.databinding.api.collection

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.internal.collections.DefaultListProperty

fun <T: Any> List<T>.toProperty(
        validator: Predicate<List<T>> = { true }
): ListProperty<T> = DefaultListProperty(this, validator)