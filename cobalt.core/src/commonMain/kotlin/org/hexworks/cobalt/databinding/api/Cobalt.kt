package org.hexworks.cobalt.databinding.api

import org.hexworks.cobalt.events.api.EventBus

public object Cobalt {

    val eventbus: EventBus = EventBus.create()
}
