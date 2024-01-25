package org.hexworks.cobalt.core.api

import org.hexworks.cobalt.core.internal.DefaultUUID

/**
 * Represents a unique identifier (UUID).
 */
interface UUID {

    companion object {

        /**
         * Creates a random [UUID].
         */
        fun randomUUID(): UUID = DefaultUUID.randomDefaultUUID()

        /**
         * Tries to create a [UUID] from a [String].
         * This will throw an exception if the [UUID] cannot be created.
         */
        fun fromString(str: String): UUID = DefaultUUID(str)
    }
}



