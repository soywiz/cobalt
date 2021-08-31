package org.hexworks.cobalt.databinding.api.extension

/**
 * Returns the value supplied by [whenNull] if [T] is `null`,
 * otherwise returns the result of applying [whenNotNull] to the value.
 */
fun <T, R> T?.fold(
    whenNull: () -> R,
    whenNotNull: (T) -> R
): R = this?.let(whenNotNull) ?: whenNull()

/**
 * Returns `this` if it is not `null`, otherwise
 * returns [other].
 */
fun <T> T?.orElse(other: T): T = this ?: other

/**
 * Returns `this` if it is not `null`, otherwise
 * returns the result of calling [other].
 */
fun <T> T?.orElseGet(other: () -> T): T = this ?: other()

/**
 * Returns the value of `this` if it is not `null`, or throws
 * the exception that's returned by [exceptionSupplier].
 */
fun <T, X : Throwable> T?.orElseThrow(
    exceptionSupplier: () -> X
): T = this ?: throw exceptionSupplier()
