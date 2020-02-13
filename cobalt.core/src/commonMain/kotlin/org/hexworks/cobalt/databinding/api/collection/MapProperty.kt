package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentMap
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue

/**
 * A [MapProperty] is a [Property] which wraps an underlying [PersistentMap].
 * @see Property
 * @see ObservableValue
 * @see WritableValue
 */
interface MapProperty<K : Any, V : Any> : ObservableMap<K, V>,
        WritableMap<K, V>, Property<PersistentMap<K, V>> {

    companion object
}
