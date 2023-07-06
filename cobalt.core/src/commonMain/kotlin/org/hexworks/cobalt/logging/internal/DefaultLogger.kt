package org.hexworks.cobalt.logging.internal

import org.hexworks.cobalt.logging.api.LogException
import org.hexworks.cobalt.logging.api.Logger

internal class DefaultLogger(override val name: String) : Logger {

    private val logger = korlibs.logger.Logger(name)

    override fun trace(t: Throwable?, msgFn: () -> String) {
        if (t == null) logger.trace(msgFn) else logger.trace { LogException(msgFn(), t) }
    }

    override fun debug(t: Throwable?, msgFn: () -> String) {
        if (t == null) logger.debug(msgFn) else logger.debug { LogException(msgFn(), t) }
    }

    override fun info(t: Throwable?, msgFn: () -> String) {
        if (t == null) logger.info(msgFn) else logger.info { LogException(msgFn(), t) }
    }

    override fun warn(t: Throwable?, msgFn: () -> String) {
        if (t == null) logger.warn(msgFn) else logger.warn { LogException(msgFn(), t) }
    }

    override fun error(t: Throwable?, msgFn: () -> String) {
        if (t == null) logger.error(msgFn) else logger.error { LogException(msgFn(), t) }
    }


}
