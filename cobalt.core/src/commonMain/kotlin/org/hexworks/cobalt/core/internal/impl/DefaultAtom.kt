package org.hexworks.cobalt.core.internal.impl

import org.hexworks.cobalt.core.internal.Atom
import kotlin.jvm.Synchronized
import kotlin.jvm.Volatile

class DefaultAtom<T>(initialValue: T) : Atom<T> {

    @Volatile
    private var value: T = initialValue

    override fun get(): T = value

    @Synchronized
    override fun transform(transformer: (T) -> T): T {
        value = transformer(value)
        return value
    }

}
