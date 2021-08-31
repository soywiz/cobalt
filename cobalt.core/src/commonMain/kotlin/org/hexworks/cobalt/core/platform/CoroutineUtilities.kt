package org.hexworks.cobalt.core.platform

import kotlinx.coroutines.GlobalScope
import kotlin.coroutines.CoroutineContext

@Suppress("EXPERIMENTAL_API_USAGE")
expect fun <T : Any> runTest(
    context: CoroutineContext = GlobalScope.coroutineContext,
    block: suspend () -> T
)
