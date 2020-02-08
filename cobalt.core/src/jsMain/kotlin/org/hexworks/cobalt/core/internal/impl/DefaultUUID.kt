package org.hexworks.cobalt.core.internal.impl

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import org.hexworks.cobalt.core.api.UUID

class DefaultUUID(private val backend: Uuid = uuid4()) : UUID {

    internal val uuid4 = uuid4()

    override fun toString() = uuid4.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as DefaultUUID

        if (uuid4 != other.uuid4) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid4.hashCode()
    }



}
