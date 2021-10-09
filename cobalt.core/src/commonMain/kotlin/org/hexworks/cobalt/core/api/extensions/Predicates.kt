package org.hexworks.cobalt.core.api.extensions

public typealias Predicate<T> = Function1<T, Boolean>

public infix fun <T> Predicate<T>.and(other: Predicate<in T>): Predicate<T> = {
    this(it) && other(it)
}

public fun <T> Predicate<T>.negate(): Predicate<T> = {
    !this(it)
}

public infix fun <T> Predicate<T>.or(other: Predicate<in T>): Predicate<T> = {
    this(it) || other(it)
}
