package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.core.api.behavior.Disposable
import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * A [Binding] computes its value based on the value of its dependencies.
 * A binding is subscribed to the changes of its dependencies and updates
 * its value whenever any of them changes.
 */
public interface Binding<out T> : ObservableValue<T>, Disposable {

    companion object
}
