package org.hexworks.cobalt.html

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import kotlin.browser.document

fun main() {
    document.addEventListener("DOMContentLoaded", object : EventListener {
        override fun handleEvent(event: Event) {
            println("Foobar")
        }
    })
}