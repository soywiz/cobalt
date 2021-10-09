package org.hexworks.cobalt.core.api.behavior

/**
 * Contains information about whether a [Disposable] object is disposed or not.
 */
public sealed class DisposeState(val isDisposed: Boolean) {
    companion object
}

/**
 * The [Disposable] is not disposed.
 */
public object NotDisposed : DisposeState(false)

/**
 * The [Disposable] was disposed by the user.
 */
public object DisposedByHand : DisposeState(true)

/**
 * The [Disposable] was disposed by some event.
 */
public data class DisposedByEvent<T : Any>(val event: T) : DisposeState(true)

/**
 * The [Disposable] was disposed due to an [Exception].
 */
public data class DisposedByException(val exception: Exception) : DisposeState(true)
