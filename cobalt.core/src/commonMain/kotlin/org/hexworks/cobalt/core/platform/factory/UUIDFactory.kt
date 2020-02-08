package org.hexworks.cobalt.core.platform.factory

import org.hexworks.cobalt.core.api.UUID

expect object UUIDFactory {

    fun randomUUID(): UUID

    fun fromString(str: String): UUID
}
