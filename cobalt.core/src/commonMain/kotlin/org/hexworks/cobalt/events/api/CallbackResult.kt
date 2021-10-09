package org.hexworks.cobalt.events.api

public sealed class CallbackResult

public object KeepSubscription : CallbackResult()

public object DisposeSubscription : CallbackResult()
