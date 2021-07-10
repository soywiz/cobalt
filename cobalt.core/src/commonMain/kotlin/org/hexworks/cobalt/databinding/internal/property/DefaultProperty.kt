package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.property.PropertyValidator
import org.hexworks.cobalt.databinding.internal.property.base.BaseProperty

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(
    initialValue: T,
    validator: PropertyValidator<T> = { _, _ -> true }
) : BaseProperty<T>(
    initialValue = initialValue,
    validator = validator
)
