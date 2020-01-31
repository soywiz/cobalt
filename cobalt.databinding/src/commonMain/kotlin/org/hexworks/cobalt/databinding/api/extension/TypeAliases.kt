package org.hexworks.cobalt.databinding.api.extension

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.PersistentSet
import org.hexworks.cobalt.databinding.api.value.ObservableValue

typealias ObservablePersistentList<T> = ObservableValue<PersistentList<T>>

typealias ObservablePersistentSet<T> = ObservableValue<PersistentSet<T>>

typealias ObservablePersistentMap<K, V> = ObservableValue<PersistentMap<K, V>>