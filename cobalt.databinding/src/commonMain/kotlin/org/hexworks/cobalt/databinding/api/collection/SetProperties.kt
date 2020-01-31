package org.hexworks.cobalt.databinding.api.collection

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.internal.collections.DefaultSetProperty

fun <T : Any> Set<T>.toProperty(
        validator: Predicate<Set<T>> = { true }
): SetProperty<T> = DefaultSetProperty(this, validator)