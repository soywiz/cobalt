package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.core.api.behavior.DisposeState
import org.hexworks.cobalt.core.api.behavior.DisposedByException
import org.hexworks.cobalt.core.api.behavior.NotDisposed
import org.hexworks.cobalt.events.api.*
import org.hexworks.cobalt.logging.api.LoggerFactory

internal class DefaultEventBus : EventBus {

    private var closed = false

    private var subscriptions = mutableMapOf<SubscriberKey, MutableList<EventBusSubscription<*>>>()
    private val logger = LoggerFactory.getLogger(this::class)

    override fun fetchSubscribersOf(eventScope: EventScope, key: String): Iterable<Subscription> {
        return subscriptions.getOrElse(SubscriberKey(eventScope, key)) { listOf() }.toList()
    }

    override fun <T : Event> subscribeTo(
        eventScope: EventScope,
        key: String,
        fn: (T) -> CallbackResult
    ): Subscription = whenNotClosed {
        try {
            logger.debug { "Subscribing to $key with scope $eventScope." }
            val subscription = EventBusSubscription(
                eventScope = eventScope,
                key = key,
                callback = fn
            )
            val subKey = SubscriberKey(eventScope, key)
            val subs = subscriptions[subKey] ?: run {
                val list = mutableListOf<EventBusSubscription<*>>()
                subscriptions[subKey] = list
                list
            }
            subs.add(subscription)
            subscription
        } catch (e: Exception) {
            logger.warn("Failed to subscribe to event key $key with scope $eventScope", e)
            throw e
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(
        event: Event,
        eventScope: EventScope
    ): Unit = whenNotClosed {
        logger.debug {
            "Publishing event with key ${event.key} and scope $eventScope."
        }
        subscriptions[SubscriberKey(eventScope, event.key)]?.let { subscribers ->
            subscribers.toList().forEach { subscription: EventBusSubscription<*> ->
                try {
                    if (subscription.callback.fixType().invoke(event) is DisposeSubscription) {
                        subscription.dispose()
                    }
                } catch (e: Exception) {
                    logger.warn("Cancelling failed subscription $subscription.", e)
                    try {
                        subscription.dispose(DisposedByException(e))
                    } catch (e: Exception) {
                        logger.warn("Failed to cancel subscription $subscription.", e)
                    }
                }
            }
        }
    }

    override fun cancelScope(scope: EventScope): Unit = whenNotClosed {
        logger.debug { "Cancelling scope $scope." }
        subscriptions.filter { it.key.scope == scope }
            .flatMap { it.value }
            .forEach {
                try {
                    it.dispose()
                } catch (e: Exception) {
                    logger.warn("Cancelling subscription failed while cancelling scope. Reason: ${e.message}")
                }
            }
    }

    override fun close() {
        closed = true
        subscriptions.values.flatten().forEach { it.dispose() }
    }

    private fun <T> whenNotClosed(fn: () -> T): T {
        return if (closed) error("This Event Bus is already closed.") else fn()
    }

    private data class SubscriberKey(val scope: EventScope, val key: String)

    private inner class EventBusSubscription<in T : Event>(
        val eventScope: EventScope,
        val key: String,
        val callback: (T) -> CallbackResult
    ) : Subscription {

        override var disposeState: DisposeState = NotDisposed
            private set

        @Suppress("UNCHECKED_CAST")
        override fun dispose(disposeState: DisposeState) {
            return try {
                logger.debug {
                    "Cancelling event bus subscription with scope '$eventScope' and key '$key'."
                }
                val key = SubscriberKey(eventScope, key)
                this.disposeState = disposeState
                subscriptions[key]?.let { subs ->
                    subs.remove(this)
                    if (subs.isEmpty()) {
                        subscriptions.remove(key)
                    }
                }
                Unit
            } catch (e: Exception) {
                logger.warn("Cancelling event bus subscription failed.", e)
                throw e
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun ((Nothing) -> CallbackResult).fixType() = this as (Event) -> CallbackResult
}
