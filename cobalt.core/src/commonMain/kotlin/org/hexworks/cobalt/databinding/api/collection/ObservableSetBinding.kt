package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import org.hexworks.cobalt.databinding.api.binding.Binding

interface ObservableSetBinding<T: Any> : ObservableSet<T>, Binding<PersistentSet<T>>