package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.databinding.internal.property.base.BaseProperty

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(
    initialValue: T,
    validator: Predicate<T> = { true }
) : BaseProperty<T>(
    initialValue = initialValue,
    validator = validator)
