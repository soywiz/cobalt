package org.hexworks.cobalt.databinding.api.converter

/**
 * Allows for converting a value [S] to a value [T]. This interface
 * (and its derivatives) are needed because in MPP one can't use plain
 * lambdas for this purpose because Javascript doesn't support it.
 * Use [((S) -> T).toConverter()] to create a [Converter] form a lambda.
 */
interface Converter<S, T> {

    fun convert(source: S): T

    companion object
}
