package org.hexworks.cobalt.core.platform.factory

import com.benasher44.uuid.uuidFrom
import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.core.internal.impl.DefaultIdentifier

actual object IdentifierFactory {

    actual fun randomIdentifier(): Identifier = DefaultIdentifier()

    actual fun fromString(str: String): Identifier = DefaultIdentifier(uuidFrom(str))

}