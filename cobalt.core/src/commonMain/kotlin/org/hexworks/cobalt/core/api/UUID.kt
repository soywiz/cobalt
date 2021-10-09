package org.hexworks.cobalt.core.api

import com.benasher44.uuid.uuid4
import com.benasher44.uuid.uuidFrom
import org.hexworks.cobalt.core.internal.DefaultUUID

/**
 * Represents a unique identifier (UUID).
 */
public interface UUID {

    companion object {

        /**
         * Creates a random [UUID].
         */
        fun randomUUID(): UUID = DefaultUUID(uuid4())

        /**
         * Tries to create a [UUID] from a [String].
         * This will throw an exception if the [UUID] cannot be created.
         */
        fun fromString(str: String): UUID = DefaultUUID(uuidFrom(str))
    }
}
