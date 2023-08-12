package com.gw.study.datastructure.heap

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class HeapTest {

    private val heapSize = 100
    private lateinit var heap: GwHeap<Int>

    @Before
    fun initialize() {
        heap = GwHeap(heapSize = heapSize) { num1, num2 -> num2 - num1 }
        repeat(heapSize) {
            heap.insert(heapSize - it)
        }
    }

    @Test
    fun heap_insert_equalsTrue() {
        assertEquals(heapSize, heap.numberOfData)
    }

    @Test
    fun heap_delete_equalsTrue() {
        repeat(heapSize) {
            assertEquals(it + 1, heap.delete())
        }
    }
}