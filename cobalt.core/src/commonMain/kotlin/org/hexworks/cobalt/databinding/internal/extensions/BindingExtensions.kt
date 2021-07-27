package org.hexworks.cobalt.databinding.internal.extensions

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.core.behavior.DisposedByException
import org.hexworks.cobalt.databinding.api.event.ChangeType

internal fun <T : Any> Binding<T>.runWithDisposeOnFailure(fn: () -> Unit) {
    try {
        fn()
    } catch (e: Exception) {
        dispose(DisposedByException(e))
    }
}
