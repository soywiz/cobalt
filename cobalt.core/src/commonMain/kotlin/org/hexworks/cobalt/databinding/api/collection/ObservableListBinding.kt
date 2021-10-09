package org.hexworks.cobalt.databinding.api.collection

import kotlinx.collections.immutable.PersistentList
import org.hexworks.cobalt.databinding.api.binding.Binding

public interface ObservableListBinding<T> : ObservableList<T>, Binding<PersistentList<T>>
