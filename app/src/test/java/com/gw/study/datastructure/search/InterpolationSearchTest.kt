package com.gw.study.datastructure.search

import com.gw.study.datastructure.sort.QuickSort
import junit.framework.TestCase
import org.junit.Test

class InterpolationSearchTest {

    @Test
    fun interpolationSearch_search_equalsTrue() {
        val intArray = intArrayOf(9,4,5,2,1,3,6,7,8,0)
        val quickSort = QuickSort()
        quickSort.sort(intArray)

        val search = InterpolationSearch()
        TestCase.assertEquals(5, search.search(intArray, 5, 0, intArray.lastIndex))
        TestCase.assertEquals(null, search.search(intArray, 10, 0, intArray.lastIndex))
    }
}