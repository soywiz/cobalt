package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.binding.Binding

interface ObservableListBinding<T: Any> : ObservableList<T>, Binding<PersistentList<T>>