package org.hexworks.cobalt.core.behavior

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Subscription

/**
 * Represents an object which holds resources and can be [dispose]d
 * to free the resources used.
 */
interface Disposable {

    /**
     * Tells whether this [Disposable] is disposed or not.
     */
    val disposed: Boolean
        get() = disposeState.isDisposed

    /**
     * Contains detailed information about the dispose state of this [Disposable].
     */
    val disposeState: DisposeState

    /**
     * Disposes this [Disposable] with the given [DisposeState].
     * Default is [DisposedByHand].
     */
    fun dispose(disposeState: DisposeState = DisposedByHand)

    /**
     * Disposes this [Disposable] when the value of [condition] becomes `true`.
     * Will immediately [dispose] this [Disposable] if [ObservableValue.value] is `true`.
     */
    infix fun disposeWhen(condition: ObservableValue<Boolean>) {
        if (condition.value) {
            dispose()
        } else {
            var subscription: Subscription? = null
            subscription = condition.onChange { (newValue) ->
                if (newValue) {
                    subscription?.dispose()
                    dispose()
                }
            }
        }
    }

    /**
     * Keeps this [Disposable] [NotDisposed] until [condition] becomes `false`.
     * Will immediately [dispose] this [Disposable] if [ObservableValue.value] is `false`.
     */
    infix fun keepUntil(condition: ObservableValue<Boolean>) {
        if (condition.value.not()) {
            dispose()
        } else {
            var subscription: Subscription? = null
            subscription = condition.onChange { (newValue) ->
                if (newValue.not()) {
                    subscription?.dispose()
                    dispose()
                }
            }
        }
    }
}