package com.gw.study.datastructure.sort

import junit.framework.TestCase.assertEquals
import org.junit.Test

class BubbleSortTest {

    @Test
    fun bubbleSort_sort_equalsTrue() {
        val bubbleSort = BubbleSort()
        val intArray = intArrayOf(5,4,3,2,1,0)
        bubbleSort.sort(intArray)

        intArray.forEachIndexed { index, i ->
            assertEquals(index, i)
        }
    }

}