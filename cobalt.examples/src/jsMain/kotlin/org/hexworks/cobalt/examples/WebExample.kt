package org.hexworks.cobalt.examples

import org.hexworks.cobalt.databinding.api.binding.bindPlusWith
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.extensions.updateFrom
import org.hexworks.cobalt.html.find
import org.hexworks.cobalt.html.withElement
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import kotlin.browser.document
import kotlin.dom.addClass

fun main() {
    document.addEventListener("DOMContentLoaded", object : EventListener {
        override fun handleEvent(event: Event) {
            init()
        }
    })
}

fun init() {
    initRegisterForm()
}

private fun initRegisterForm() {

    val form = document.find<HTMLFormElement>("#registerForm").get()
    val firstName = "".toProperty {
        it.isNotBlank()
    }
    val spacer = " ".toProperty()
    val lastName = "".toProperty {
        it.isNotBlank()
    }
    val fullName = firstName bindPlusWith spacer bindPlusWith lastName

    document.withElement<HTMLInputElement>("#firstName") { element ->
        firstName.updateFrom(element, "First Name is missing")
        firstName.onChange { form.addClass("was-validated") }
    }

    document.withElement<HTMLInputElement>("#lastName") { element ->
        lastName.updateFrom(element, "Last Name is missing")
        lastName.onChange { form.addClass("was-validated") }
    }

    document.withElement<HTMLInputElement>("#fullName") { element ->
        element.updateFrom(fullName)
    }
}