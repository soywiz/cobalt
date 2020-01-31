package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue

/**
 * A [ListProperty] is a [Property] which wraps an underlying [PersistentList].
 * @see Property
 * @see ObservableValue
 * @see WritableValue
 */
interface ListProperty<T : Any> : ObservableList<T>, WritableList<T>, Property<PersistentList<T>> {

    companion object
}
