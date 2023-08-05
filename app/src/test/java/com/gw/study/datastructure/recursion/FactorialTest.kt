package com.gw.study.datastructure.recursion

import junit.framework.TestCase.assertEquals
import org.junit.Test

class FactorialTest {

    @Test
    fun factorial_calc_equalsTure() {
        with(Factorial()) {
            assertEquals(1, calc(1))
            assertEquals(362880, calc(9))
        }
    }


}