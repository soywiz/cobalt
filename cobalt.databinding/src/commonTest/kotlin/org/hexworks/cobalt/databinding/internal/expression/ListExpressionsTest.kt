package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.collection.toProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName")
class ListExpressionsTest {

    private val prop0 = NUMBERS_1_TO_3.toProperty()
    private val prop1 = NUMBERS_4_TO_6.toProperty()

    @Test
    fun test() {

        val target = prop0.plus(prop1)

        assertEquals(NUMBERS_1_TO_3.plus(NUMBERS_4_TO_6), target)
    }

    companion object {

        val NUMBERS_1_TO_3 = listOf(1, 2, 3)
        val NUMBERS_4_TO_6 = listOf(4, 5, 6)
    }
}
