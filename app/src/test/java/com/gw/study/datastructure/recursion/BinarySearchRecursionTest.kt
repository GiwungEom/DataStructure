package com.gw.study.datastructure.recursion

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class BinarySearchRecursionTest {

    private lateinit var binarySearch: BinarySearchByRecursive<Int>
    private lateinit var array: Array<Int>

    @Before
    fun init() {
        binarySearch = BinarySearchByRecursive { p1, p2 -> p1 - p2 }
        array = Array(50) { it }
    }

    @Test
    fun binarySearch_search_equalsTrue() {
        TestCase.assertEquals(5, binarySearch.search(array, 5, 0, array.lastIndex))
        TestCase.assertEquals(1, binarySearch.search(array, 1, 0, array.lastIndex))
        TestCase.assertEquals(null, binarySearch.search(array, 100, 0, array.lastIndex))
    }

}