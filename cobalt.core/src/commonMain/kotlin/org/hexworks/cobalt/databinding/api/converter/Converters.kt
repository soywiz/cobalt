package org.hexworks.cobalt.databinding.api.converter

fun <S, T> ((S) -> T).toConverter() = object : Converter<S, T> {
    override fun convert(source: S) = invoke(source)
}
