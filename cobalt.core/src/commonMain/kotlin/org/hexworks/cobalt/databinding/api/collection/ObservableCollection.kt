package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentCollection
import org.hexworks.cobalt.databinding.api.event.ChangeType
import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * An [ObservableCollection] is an [ObservableValue] which wraps an underlying
 * [PersistentCollection] and can be used to track its changes with [onChange].
 */
interface ObservableCollection<T, C : PersistentCollection<T>> : PersistentCollection<T>, ObservableValue<C> {

    companion object
}
