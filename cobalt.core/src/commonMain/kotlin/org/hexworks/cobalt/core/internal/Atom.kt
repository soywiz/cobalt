package org.hexworks.cobalt.core.internal

import org.hexworks.cobalt.core.internal.internal.DefaultAtom

interface Atom<T> {

    fun get(): T

    fun transform(transformer: (T) -> T): T

    companion object {

        fun <T> fromObject(obj: T): Atom<T> = DefaultAtom(obj)
    }
}

fun <T> T.toAtom(): Atom<T> = Atom.fromObject(this)
