package org.hexworks.cobalt.databinding.api.property

typealias PropertyValidator<T> = (oldValue: T, newValue: T) -> Boolean