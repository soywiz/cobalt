package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentSet
import org.hexworks.cobalt.databinding.api.value.Value

/**
 * A [WritableSet] is a [Value] which wraps an underlying [PersistentSet]
 * and allows changing its [value].
 */
interface WritableSet<T : Any> : WritableCollection<T, PersistentSet<T>> {

    companion object
}
