package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.event.ListChange

interface ObservableListBinding<T : Any> : ObservableList<T>, Binding<PersistentList<T>>
