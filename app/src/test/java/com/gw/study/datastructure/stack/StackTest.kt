package com.gw.study.datastructure.stack

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class StackTest {

    private lateinit var stackOnArray: GwStackBasedOnArray<Int>
    private val stackSize = 5

    @Before
    fun initialize() {
        stackOnArray = GwStackBasedOnArray(stackSize)
        repeat(stackSize) {
            stackOnArray.push(it)
        }
    }

    @Test
    fun stackBasedOnArray_push_equalsTrue() {
        assertEquals(stackSize, stackOnArray.numberOfData)
    }

    @Test
    fun stackBasedOnArray_pop_equalsTrue() {
        repeat(stackSize) {
            assertEquals((stackSize - 1) - it, stackOnArray.pop())
        }
    }

    @Test
    fun stackBasedOnArray_peek_equalsTrue() {
        repeat(stackSize) {
            assertEquals((stackSize - 1) - it, stackOnArray.peek())
            stackOnArray.pop()
        }
    }
}