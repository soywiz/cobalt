package org.hexworks.cobalt.databinding.api.collection

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.internal.collections.DefaultMapProperty

fun <K : Any, V : Any> Map<K, V>.toProperty(
        validator: Predicate<Map<K, V>> = { true }
): MapProperty<K, V> = DefaultMapProperty(this, validator)