package org.hexworks.cobalt.databinding.api.extension

import org.hexworks.cobalt.events.api.Subscription

/**
 * Clears the [Subscription]s in this [MutableList] and also clears this list.
 */
fun <T : Subscription> MutableList<T>.disposeSubscriptions() {
    forEach {
        try {
            it.dispose()
        } catch (e: Exception) {
            println("Cancelling subscription failed: ${e.message}.")
        }
    }
    clear()
}
