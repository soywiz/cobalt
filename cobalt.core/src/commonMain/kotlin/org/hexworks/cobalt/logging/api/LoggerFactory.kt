package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.logging.internal.DefaultLogger
import kotlin.reflect.KClass

public object LoggerFactory {

    fun getLogger(name: String): Logger = DefaultLogger(name)

    fun getLogger(kClass: KClass<out Any>): Logger = getLogger(kClass.simpleName ?: "name-is-missing-try-using-the-string-overload")
}
