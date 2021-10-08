package org.hexworks.cobalt.databinding.internal.extensions

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.core.api.behavior.DisposedByException

internal fun <T> Binding<T>.runWithDisposeOnFailure(fn: () -> Unit) {
    try {
        fn()
    } catch (e: Exception) {
        dispose(DisposedByException(e))
    }
}
