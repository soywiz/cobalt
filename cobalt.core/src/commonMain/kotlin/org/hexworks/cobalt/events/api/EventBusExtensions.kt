package org.hexworks.cobalt.events.api

import org.hexworks.cobalt.events.internal.ApplicationScope

/**
 * Same as [subscribeTo], but will always [KeepSubscription] when
 * the callback is called.
 */
inline fun <reified T : Event> EventBus.simpleSubscribeTo(
    eventScope: EventScope = ApplicationScope,
    noinline callback: (T) -> Unit
): Subscription {
    val key = T::class.simpleName ?: throw IllegalArgumentException(
        "Event class doesn't have a name: ${T::class}"
    )
    return subscribeTo<T>(
        eventScope = eventScope,
        key = key,
        fn = {
            callback(it)
            KeepSubscription
        }
    )
}

/**
 * Reified variant of [EventBus.simpleSubscribeTo].
 */
inline fun <reified T : Event> EventBus.subscribeTo(
    eventScope: EventScope = ApplicationScope,
    noinline callback: (T) -> CallbackResult
): Subscription {
    val key = T::class.simpleName ?: throw IllegalArgumentException(
        "Event class doesn't have a name: ${T::class}"
    )
    return subscribeTo(
        eventScope = eventScope,
        key = key,
        fn = callback
    )
}
