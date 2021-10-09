package org.hexworks.cobalt.core.api.extensions

import org.hexworks.cobalt.core.api.UUID

/**
 * Returns the first 4 characters of this [UUID].
 */
public fun UUID.abbreviate() = toString().substring(0, 4)
