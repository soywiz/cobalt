package org.hexworks.cobalt.core.internal.impl

import org.hexworks.cobalt.core.api.UUID

data class DefaultUUID(
        private val backend: java.util.UUID = java.util.UUID.randomUUID()
) : UUID {
    override fun toString() = backend.toString()
}
