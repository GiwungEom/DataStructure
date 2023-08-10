package com.gw.study.datastructure.queue

import junit.framework.TestCase.assertEquals
import org.junit.Test

class QueueTest {
    private val queueSize = 10
    private val queueBasedOnArray: GwQueue<Int> by lazy { GwQueueBasedOnArray(queueSize + 1) }
    private val queueBasedOnLinkedList: GwQueue<Int> by lazy { GwQueueBasedOnLinkedList() }

    private infix fun GwQueue<Int>.generateData(queueSize: Int) {
        repeat(queueSize) { enqueue(it) }
    }

    @Test
    fun queueBasedOnArray_enqueue_equalsTrue() {
        queueBasedOnArray generateData queueSize
        assertEquals(queueSize, queueBasedOnArray.numberOfData)
    }

    @Test
    fun queueBasedOnArray_dequeue_equalsTrue() {
        queueBasedOnArray generateData queueSize
        repeat(queueSize) {
            assertEquals(it, queueBasedOnArray.dequeue())
        }
    }

    @Test
    fun queueBasedOnArray_peek_equalsTrue() {
        queueBasedOnArray generateData queueSize
        repeat(queueSize) {
            assertEquals(it, queueBasedOnArray.peek())
            queueBasedOnArray.dequeue()
        }
    }

    @Test
    fun queueBasedOnLinkedList_enqueue_equalsTrue() {
        queueBasedOnLinkedList generateData queueSize
        assertEquals(queueSize, queueBasedOnLinkedList.numberOfData)
    }

    @Test
    fun queueBasedOnLinkedList_dequeue_equalsTrue() {
        queueBasedOnLinkedList generateData queueSize
        repeat(queueSize) {
            assertEquals(it, queueBasedOnLinkedList.dequeue())
        }
    }

    @Test
    fun queueBasedOnLinkedList_peek_equalsTrue() {
        queueBasedOnLinkedList generateData queueSize
        repeat(queueSize) {
            assertEquals(it, queueBasedOnLinkedList.peek())
            queueBasedOnLinkedList.dequeue()
        }
    }
}