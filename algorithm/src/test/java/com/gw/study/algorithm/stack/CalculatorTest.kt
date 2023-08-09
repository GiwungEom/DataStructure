package com.gw.study.algorithm.stack

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun calculator_calc_equalsTrue() {
        val infixExpression = "((3+5)*3)*2"
        val result = Calculator().calc(infixExpression)
        assertEquals(48, result)
    }

}