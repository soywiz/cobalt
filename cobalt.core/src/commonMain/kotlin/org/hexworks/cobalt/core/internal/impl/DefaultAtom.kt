package org.hexworks.cobalt.core.internal.impl

import org.hexworks.cobalt.core.internal.Atom

class DefaultAtom<T>(initialValue: T) : Atom<T> {

    private var value: T = initialValue

    override fun get(): T = value

    override fun transform(transformer: (T) -> T): T {
        value = transformer(value)
        return value
    }

}
