package org.hexworks.cobalt.events.api

import org.hexworks.cobalt.events.internal.ApplicationScope
import org.hexworks.cobalt.events.internal.DefaultEventBus

/**
 * An [EventBus] can be used to broadcast [Event]s to subscribers of that [Event].
 */
interface EventBus {

    /**
     * Returns all subscribers of the event with the given [key] and [eventScope].
     */
    fun fetchSubscribersOf(
        eventScope: EventScope = ApplicationScope,
        key: String
    ): Iterable<Subscription>

    /**
     * Subscribes the callee to [Event]s which have [eventScope] and [key].
     * [fn] will be called whenever there is a match. [CallbackResult] can
     * be used to control the [Subscription]:
     * - [KeepSubscription] will keep the [Subscription]
     * - [DisposeSubscription] will dispose it
     */
    fun <T : Event> subscribeTo(
        eventScope: EventScope = ApplicationScope,
        key: String,
        fn: (T) -> CallbackResult
    ): Subscription

    /**
     * Publishes the given [Event] to all listeners who have the same
     * [eventScope] and [Event.key].
     */
    fun publish(
        event: Event,
        eventScope: EventScope = ApplicationScope
    )


    /**
     * Cancels all [Subscription]s for the given [scope].
     */
    fun cancelScope(scope: EventScope)

    /**
     * Cancels all subscriptions and closes this [EventBus].
     */
    fun close()

    companion object {

        /**
         * Creates a new [EventBus].
         */
        fun create(): EventBus = DefaultEventBus()
    }

}
