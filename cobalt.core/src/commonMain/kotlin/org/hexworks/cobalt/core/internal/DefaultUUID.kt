package org.hexworks.cobalt.core.internal

import org.hexworks.cobalt.core.api.UUID


internal class DefaultUUID(private val backend: korlibs.io.util.UUID) : UUID {
    override fun toString() = backend.toString()
}
