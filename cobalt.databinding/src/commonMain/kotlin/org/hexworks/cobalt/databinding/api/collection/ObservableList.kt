package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * An [ObservableList] is an [ObservableValue] which wraps an underlying
 * [PersistentList] and can be used to track its changes with [onChange].
 */
interface ObservableList<T : Any> : ObservableCollection<T, PersistentList<T>>, PersistentList<T> {

    companion object
}
