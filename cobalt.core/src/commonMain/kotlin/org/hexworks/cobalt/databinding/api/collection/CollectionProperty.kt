package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentCollection
import org.hexworks.cobalt.databinding.api.event.ChangeType
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue

/**
 * A [CollectionProperty] is a [Property] which wraps an underlying [PersistentCollection].
 * @see Property
 * @see ObservableValue
 * @see WritableValue
 */
interface CollectionProperty<T : Any, C : PersistentCollection<T>> :
    ObservableCollection<T, C>, WritableCollection<T, C>, Property<C> {

    companion object
}
