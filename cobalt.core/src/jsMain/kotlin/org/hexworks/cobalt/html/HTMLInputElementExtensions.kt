package org.hexworks.cobalt.html

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

val HTMLInputElement.trimmedValue: String
    get() = this.value.trim()

fun HTMLInputElement.setValue(value: String) {
    this.value = value
}

fun HTMLInputElement.clearValue() {
    this.value = ""
}

fun HTMLInputElement.onEscapePressed(listener: (KeyboardEvent) -> Unit) {
    this.addEventListener("keypress", { e ->
        // keypress does not work for Escape for some reason
        if (e is KeyboardEvent) {
            if (e.keyCode == 27) {
                listener.invoke(e)
            }
        }
    }, false)
}

fun HTMLInputElement.onEnterPressed(listener: (KeyboardEvent) -> Unit) {
    this.addEventListener("keypress", { e ->
        if (e is KeyboardEvent) {
            if (e.keyCode == 13) {
                listener.invoke(e)
            }
        }
    }, false)
}

fun HTMLInputElement.onBlur(listener: (Event) -> Unit) {
    this.addEventListener("blur", { e ->
        listener.invoke(e)
    }, false)
}

fun HTMLInputElement.toggle() {
    val checked = this.defaultChecked
    this.checked = checked.not()
    this.defaultChecked = checked.not()
}
