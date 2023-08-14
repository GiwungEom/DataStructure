package com.gw.study.datastructure.sort

import junit.framework.TestCase
import org.junit.Test

class RadixSortTest {

    @Test
    fun radixSort_sort_equalsTrue() {
        val intArray = intArrayOf(110, 5, 240, 466, 999, 546, 555)
        val intArrayResult = intArray.copyOf()

        val mergeSort = MergeSort()
        mergeSort.sort(intArrayResult)

        val radixSort = RadixSort()
        radixSort.sort(intArray, 10, 3)

        for (i in intArray.indices) {
            TestCase.assertEquals(intArrayResult[i], intArray[i])
        }
    }
}