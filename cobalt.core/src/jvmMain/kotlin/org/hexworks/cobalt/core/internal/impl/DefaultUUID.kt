package org.hexworks.cobalt.core.internal.impl

import org.hexworks.cobalt.core.api.UUID

class DefaultUUID(
    private val backend: java.util.UUID = java.util.UUID.randomUUID()
) : UUID {

    override fun toString() = backend.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultUUID

        if (backend != other.backend) return false

        return true
    }

    override fun hashCode(): Int {
        return backend.hashCode()
    }


}
