package org.hexworks.cobalt.core.extensions

/**
 * Creates an identity function for [T].
 */
fun <T> identity(): (T) -> T = { it }
