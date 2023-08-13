package com.gw.study.datastructure.sort

import junit.framework.TestCase
import org.junit.Test

class InsertionSortTest {

    @Test
    fun insertSort_sort_equalsTrue() {
        val intArray = intArrayOf(9,4,5,2,1,3,6,7,8,0)
        val insertSort = InsertionSort()
        insertSort.sort(intArray)
        repeat(10) {
            TestCase.assertEquals(it, intArray[it])
        }
    }

}