package com.gw.study.datastructure.search

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class LinearSearchTest {

    private lateinit var linearSearch: LinearSearch<Int>
    private lateinit var array: Array<Int>

    @Before
    fun init() {
        linearSearch = LinearSearch()
        array = Array(5) { it }
    }

    @Test
    fun linearSearch_search_equalsTrue() {
        assertEquals(3, linearSearch.search(array, 3))
    }
}