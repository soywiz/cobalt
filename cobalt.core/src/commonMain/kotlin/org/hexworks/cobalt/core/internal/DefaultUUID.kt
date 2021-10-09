package org.hexworks.cobalt.core.internal

import com.benasher44.uuid.Uuid
import org.hexworks.cobalt.core.api.UUID

internal class DefaultUUID(private val backend: Uuid) : UUID {
    override fun toString() = backend.toString()
}
