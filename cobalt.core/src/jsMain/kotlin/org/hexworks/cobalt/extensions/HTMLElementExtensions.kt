package org.hexworks.cobalt.extensions

import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.ValueValidationFailed
import org.hexworks.cobalt.databinding.api.value.ValueValidationResult
import org.hexworks.cobalt.databinding.api.value.ValueValidationSuccessful
import org.w3c.dom.HTMLInputElement

fun Property<String>.updateFrom(
        inputElement: HTMLInputElement,
        fn: (ValueValidationResult<String>) -> Unit = {}
) {
    val prop = this
    inputElement.addEventListener(type = "input", callback = {
        fn(prop.updateValue(inputElement.value))
    })
}

fun Property<String>.updateFrom(
        inputElement: HTMLInputElement,
        validationMessage: String
) {
    val prop = this
    inputElement.addEventListener(type = "input", callback = {
        when (prop.updateValue(inputElement.value)) {
            is ValueValidationFailed<String> -> {
                inputElement.setCustomValidity(validationMessage)
            }
            is ValueValidationSuccessful<String> -> {
                inputElement.setCustomValidity("")
            }
        }
    })
}

fun HTMLInputElement.updateFrom(observable: ObservableValue<String>) {
    val element = this
    observable.onChange {
        element.value = observable.value
    }
}

fun Property<String>.bind(inputElement: HTMLInputElement) {
    this.updateFrom(inputElement)
    inputElement.updateFrom(this)
}

fun HTMLInputElement.bind(
        observable: Property<String>,
        fn: (ValueValidationResult<String>) -> Unit = {}
) {
    this.updateFrom(observable)
    observable.updateFrom(this, fn)
}

