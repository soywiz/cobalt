package org.hexworks.cobalt.databinding.api.value

public class ValueValidationFailedException(
    val newValue: Any?,
    message: String
) : RuntimeException(message) {

    companion object
}
