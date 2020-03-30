package org.hexworks.cobalt.core.platform.factory

import com.benasher44.uuid.uuidFrom
import org.hexworks.cobalt.core.api.UUID
import org.hexworks.cobalt.core.internal.impl.DefaultUUID

actual object UUIDFactory {

    actual fun randomUUID(): UUID = DefaultUUID()

    actual fun fromString(str: String): UUID = DefaultUUID(uuidFrom(str))

}