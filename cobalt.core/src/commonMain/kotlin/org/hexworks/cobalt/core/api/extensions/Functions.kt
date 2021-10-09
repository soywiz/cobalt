package org.hexworks.cobalt.core.api.extensions

/**
 * Creates an identity function for [T].
 */
public fun <T> identity(): (T) -> T = { it }
