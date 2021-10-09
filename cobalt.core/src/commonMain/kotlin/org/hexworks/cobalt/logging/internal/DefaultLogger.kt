package org.hexworks.cobalt.logging.internal

import mu.KotlinLogging
import org.hexworks.cobalt.logging.api.Logger

internal class DefaultLogger(override val name: String) : Logger {

    private val logger = KotlinLogging.logger(name)

    override fun trace(msg: String) = logger.trace { msg }

    override fun trace(msgFn: () -> String) = logger.trace(msgFn)

    override fun trace(msg: String, t: Throwable) = logger.trace(t) { msg }

    override fun trace(msgFn: () -> String, t: Throwable) = logger.trace(t, msgFn)

    override fun debug(msg: String) = logger.debug { msg }

    override fun debug(msgFn: () -> String) = logger.debug(msgFn)

    override fun debug(msg: String, t: Throwable) = logger.debug(t) { msg }

    override fun debug(msgFn: () -> String, t: Throwable) = logger.debug(t, msgFn)

    override fun info(msg: String) = logger.info { msg }

    override fun info(msgFn: () -> String) = logger.info(msgFn)

    override fun info(msg: String, t: Throwable) = logger.info(t) { msg }

    override fun info(msgFn: () -> String, t: Throwable) = logger.info(t, msgFn)

    override fun warn(msg: String) = logger.warn { msg }

    override fun warn(msgFn: () -> String) = logger.warn(msgFn)

    override fun warn(msg: String, t: Throwable) = logger.warn(t) { msg }

    override fun warn(msgFn: () -> String, t: Throwable) = logger.warn(t, msgFn)

    override fun error(msg: String) = logger.error { msg }

    override fun error(msgFn: () -> String) = logger.error(msgFn)

    override fun error(msg: String, t: Throwable) = logger.error(t) { msg }

    override fun error(msgFn: () -> String, t: Throwable) = logger.error(t, msgFn)
}
