package org.hexworks.cobalt.core.platform.factory

import org.hexworks.cobalt.core.api.UUID
import org.hexworks.cobalt.core.internal.impl.DefaultUUID

actual object UUIDFactory {

    actual fun randomUUID(): UUID {
        return DefaultUUID()
    }

    actual fun fromString(str: String): UUID {
        return DefaultUUID(java.util.UUID.fromString(str))
    }

}