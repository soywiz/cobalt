package org.hexworks.cobalt.databinding.api.extension

import kotlinx.collections.immutable.PersistentCollection
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.PersistentSet
import org.hexworks.cobalt.databinding.api.value.ObservableValue

public typealias ObservablePersistentCollection<T> = ObservableValue<PersistentCollection<T>>

public typealias ObservablePersistentList<T> = ObservableValue<PersistentList<T>>

public typealias ObservablePersistentSet<T> = ObservableValue<PersistentSet<T>>

public typealias ObservablePersistentMap<K, V> = ObservableValue<PersistentMap<K, V>>
