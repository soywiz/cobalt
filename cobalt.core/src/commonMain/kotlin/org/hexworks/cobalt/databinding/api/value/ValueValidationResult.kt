package org.hexworks.cobalt.databinding.api.value

public sealed class ValueValidationResult<T>(
    val successful: Boolean
) {

    abstract val value: T

    companion object
}

public data class ValueValidationFailed<T>(
    override val value: T,
    val cause: ValueValidationFailedException
) : ValueValidationResult<T>(false)

public data class ValueValidationSuccessful<T>(
    override val value: T
) : ValueValidationResult<T>(true)
