package org.hexworks.cobalt.html

import org.hexworks.cobalt.datatypes.Maybe
import org.w3c.dom.Document
import org.w3c.dom.HTMLElement

@Suppress("UNCHECKED_CAST")
inline fun <reified T : HTMLElement> Document.find(selector: String): Maybe<T> {
    return Maybe.ofNullable(this.querySelector(selector) as? T)
}

inline fun <reified T : HTMLElement> Document.withElement(
        selector: String,
        noinline fn: (T) -> Unit
) {
    find<T>(selector).map(fn).orElseThrow {
        RuntimeException("Can't find element of type ${T::class} with selector $selector.")
    }
}
