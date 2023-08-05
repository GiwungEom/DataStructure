package com.gw.study.datastructure.recursion

import junit.framework.TestCase.assertEquals
import org.junit.Test

class FibonacciSequenceTest {

    @Test
    fun fibonacciSequence_calc_equalsTrue() {
        with(FibonacciSequence()) {
            assertEquals(0, calc(1))
            assertEquals(1, calc(2))
            assertEquals(34, calc(10))
        }
    }

}