package org.hexworks.cobalt.databinding.api.property

public typealias PropertyValidator<T> = (oldValue: T, newValue: T) -> Boolean
