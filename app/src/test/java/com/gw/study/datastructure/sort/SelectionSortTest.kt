package com.gw.study.datastructure.sort

import junit.framework.TestCase.assertEquals
import org.junit.Test

class SelectionSortTest {

    @Test
    fun selectionSort_sort_equalsTrue() {
        val selectionSort = SelectionSort()
        val intArray = intArrayOf(9,4,5,2,1,3,6,7,8,0)
        selectionSort.sort(intArray)
        repeat(10) {
            assertEquals(it, intArray[it])
        }
    }
}