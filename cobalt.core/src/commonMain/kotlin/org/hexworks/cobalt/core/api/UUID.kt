package org.hexworks.cobalt.core.api

import org.hexworks.cobalt.core.platform.factory.UUIDFactory

/**
 * Represents a unique identifier (UUID).
 */
interface UUID {

    companion object {

        fun randomUUID(): UUID = UUIDFactory.randomUUID()

        fun fromString(str: String): UUID = UUIDFactory.fromString(str)
    }
}
