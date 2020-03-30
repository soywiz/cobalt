package org.hexworks.cobalt.logging.api

enum class LoggingLevel(private val precedence: Int) {
    TRACE(0),
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    val isEnabled: Boolean
        get() = this.precedence >= LoggingSettings.loggingLevel.precedence
}
