package com.gw.study.datastructure.search

import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class AvlTreeTest {

    private lateinit var avlTree: AvlTree

    @Before
    fun initialize() {
        avlTree = AvlTree()
        repeat(11) {
            avlTree.insert(it, it)
        }
    }

    @Test
    fun avlTree_insert_equalsTrue() {
        val result = GwQueueBasedOnLinkedList<Int>().apply {
            enqueue(3)
            enqueue(1)
            enqueue(0)
            enqueue(2)
            enqueue(7)
            enqueue(5)
            enqueue(4)
            enqueue(6)
            enqueue(9)
            enqueue(8)
            enqueue(10)
        }
        avlTree.traversal {
            assertEquals(result.dequeue(), it.key)
            println("slot : $it")
        }
    }

    @Test
    fun avlTree_search_equalsTrue() {
        assertEquals(5, avlTree.search(5))
        assertEquals(null, avlTree.search(100))
    }

    @Test
    fun avlTree_remove_equalsTrue() {
        avlTree.remove(7)
        val result = GwQueueBasedOnLinkedList<Int>().apply {
            enqueue(3)
            enqueue(1)
            enqueue(0)
            enqueue(2)
            enqueue(8)
            enqueue(5)
            enqueue(4)
            enqueue(6)
            enqueue(9)
            enqueue(10)
        }
        avlTree.traversal {
            assertEquals(result.dequeue(), it.key)
            println("slot : $it")
        }
    }

}