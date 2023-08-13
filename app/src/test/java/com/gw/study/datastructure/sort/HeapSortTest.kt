package com.gw.study.datastructure.sort

import junit.framework.TestCase.assertEquals
import org.junit.Test

class HeapSortTest {

    @Test
    fun heapSort_sort_equalsTrue() {
        val intArray = intArrayOf(9,4,5,2,1,3,6,7,8,0)
        val heapSort = HeapSort()
        heapSort.sort(intArray)
        repeat(10) {
            assertEquals(it, intArray[it])
        }
    }
}