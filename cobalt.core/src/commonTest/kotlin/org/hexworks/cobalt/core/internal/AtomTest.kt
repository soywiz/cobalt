package org.hexworks.cobalt.core.internal

import kotlin.test.Test
import kotlin.test.assertEquals

class AtomTest {

    @Test
    fun test() {
        val ref = 1.toAtom()

        ref.transform {
            2
        }
        assertEquals(2, ref.get())
    }
}
