package org.hexworks.cobalt.databinding.api.converter

public class IdentityConverter<T> : IsomorphicConverter<T, T> {

    override fun convert(source: T) = source

    override fun convertBack(target: T) = target
}
