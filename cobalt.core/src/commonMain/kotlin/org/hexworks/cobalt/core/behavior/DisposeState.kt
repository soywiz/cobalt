package org.hexworks.cobalt.core.behavior

/**
 * Contains information about whether a [Disposable] object is disposed or not.
 */
sealed class DisposeState(val isDisposed: Boolean) {
    companion object
}

/**
 * The [Disposable] is not disposed.
 */
object NotDisposed : DisposeState(false)

/**
 * The [Disposable] was disposed by the user.
 */
object DisposedByHand : DisposeState(true)

/**
 * The [Disposable] was disposed by some event.
 */
data class DisposedByEvent<T : Any>(val event: T) : DisposeState(true)

/**
 * The [Disposable] was disposed due to an [Exception].
 */
data class DisposedByException(val exception: Exception) : DisposeState(true)
