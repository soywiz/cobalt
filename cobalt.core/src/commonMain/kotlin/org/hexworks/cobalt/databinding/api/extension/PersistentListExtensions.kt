package org.hexworks.cobalt.databinding.api.extension

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

public inline fun <T, R> PersistentList<T>.map(transform: (T) -> R): PersistentList<R> {
    var result = persistentListOf<R>()
    forEach { result = result.add(transform(it)) }
    return result
}