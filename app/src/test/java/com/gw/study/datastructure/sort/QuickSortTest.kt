package com.gw.study.datastructure.sort

import junit.framework.TestCase
import org.junit.Test

class QuickSortTest {

    @Test
    fun mergeSort_sort_equalsTrue() {
        val intArray = intArrayOf(9,4,5,2,1,3,6,7,8,0)
        val quickSort = QuickSort()
        quickSort.sort(intArray)
        repeat(10) {
            TestCase.assertEquals(it, intArray[it])
        }
    }
}