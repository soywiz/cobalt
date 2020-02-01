package org.hexworks.cobalt.examples

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import kotlin.browser.document
import kotlin.dom.appendText

fun main() {
    document.addEventListener("DOMContentLoaded", object : EventListener {
        override fun handleEvent(event: Event) {
            document.querySelector("body")?.appendText("foo")
        }
    })
}