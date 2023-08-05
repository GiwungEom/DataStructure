package com.gw.study.datastructure.search

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class BinarySearchTest {

    private lateinit var binarySearch: BinarySearch<Int>
    private lateinit var array: Array<Int>

    @Before
    fun init() {
        binarySearch = BinarySearch { p1, p2 -> p1 - p2 }
        array = Array(50) { it }
    }

    @Test
    fun binarySearch_search_equalsTrue() {
        assertEquals(5, binarySearch.search(array, 5))
        assertEquals(1, binarySearch.search(array, 1))
        assertEquals(null, binarySearch.search(array, 100))
    }
}