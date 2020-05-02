package org.hexworks.cobalt.core.internal.impl

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import org.hexworks.cobalt.core.api.UUID

data class DefaultUUID(
        private val backend: Uuid = uuid4()
) : UUID {
    override fun toString() = backend.toString()
}