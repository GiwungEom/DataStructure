package com.gw.study.datastructure.dequeue

import com.gw.study.datastructure.deque.GwDequeue
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GwDequeueTest {

    @Test
    fun dequeue_addFirstRemoveFirst_equalsTrue() {
        val dequeueSize = 10
        val dequeue = GwDequeue<Int>()
        repeat(dequeueSize) {
            dequeue.addFirst(it)
        }
        assertEquals(dequeueSize, dequeue.numberOfData)
        repeat(dequeueSize) {
            assertEquals((dequeueSize - 1) - it, dequeue.removeFirst())
        }
    }

    @Test
    fun dequeue_addLastRemoveLast_equalsTrue() {
        val dequeueSize = 10
        val dequeue = GwDequeue<Int>()
        repeat(dequeueSize) {
            dequeue.addLast(it)
        }
        assertEquals(dequeueSize, dequeue.numberOfData)
        repeat(dequeueSize) {
            assertEquals((dequeueSize - 1) - it, dequeue.removeLast())
        }
    }


}