package org.hexworks.cobalt.databinding.api.value

sealed class ValueValidationResult<T : Any> {

    abstract val value: T

    companion object
}

data class ValueValidationFailed<T : Any>(
        override val value: T,
        val cause: ValueValidationFailedException
) : ValueValidationResult<T>()

data class ValueValidationSuccessful<T : Any>(
        override val value: T
) : ValueValidationResult<T>()