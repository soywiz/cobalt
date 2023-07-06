package org.hexworks.cobalt.events.api

sealed class CallbackResult

object KeepSubscription : CallbackResult()

object DisposeSubscription : CallbackResult()
