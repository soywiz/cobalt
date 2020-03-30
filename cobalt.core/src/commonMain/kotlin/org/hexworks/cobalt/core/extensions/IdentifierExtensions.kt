package org.hexworks.cobalt.core.extensions

import org.hexworks.cobalt.core.api.UUID

fun UUID.abbreviate() = toString().substring(0, 4)