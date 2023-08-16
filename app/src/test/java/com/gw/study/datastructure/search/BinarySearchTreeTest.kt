package com.gw.study.datastructure.search

import com.gw.study.datastructure.queue.GwQueueBasedOnArray
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

/**
 *  Node Tree Based On web link
 *  @see [link](https://www.google.com/url?sa=i&url=https%3A%2F%2Fpjchender.blogspot.com%2F2020%2F05%2Fbinary-tree.html&psig=AOvVaw0r_j-9awQUIoWKm5Gw2zzh&ust=1692238673610000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCLCg9LmO4IADFQAAAAAdAAAAABAJ)
 */
class BinarySearchTreeTest {

    private lateinit var binarySearchTree: BinarySearchTree
    private val dataSize = 13

    @Before
    fun initialize() {
        binarySearchTree = BinarySearchTree()
        val treeDataList = listOf(50, 30, 20, 10, 45, 35, 70, 60, 59, 100, 85, 105)
        treeDataList.forEach { binarySearchTree.insert(it, it) }
    }

    @Test
    fun binarySearchTree_insert_equalsTrue() {
        // preorder Only
        val resultQueue = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(50)
            enqueue(30)
            enqueue(20)
            enqueue(10)
            enqueue(45)
            enqueue(35)
            enqueue(70)
            enqueue(60)
            enqueue(59)
            enqueue(100)
            enqueue(85)
            enqueue(105)
        }

        binarySearchTree.preOrderTraversal {
            assertEquals(resultQueue.dequeue(), it.key)
        }
    }

    @Test
    fun binarySearchTree_search_equalsTrue() {
        assertEquals(50, binarySearchTree.search(50))
        assertEquals(null, binarySearchTree.search(200))
    }

    /**
     * 삭제 케이스
     *  단말 노드 삭제
     *  하나의 서브 트리 삭제
     *  두개의 서브 트리 삭제
     *  루트 삭제
     */
    @Test
    fun binarySearchTree_remove_terminalNode_equalsTrue() {
        val result = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(50)
            enqueue(30)
            enqueue(20)
            enqueue(45)
            enqueue(35)
            enqueue(70)
            enqueue(60)
            enqueue(59)
            enqueue(100)
            enqueue(85)
            enqueue(105)
        }
        // 단말 노드 삭제
        binarySearchTree.remove(10)

        // 전체 데이터 검사
        binarySearchTree.preOrderTraversal {
            assertEquals(result.dequeue(), it.key)
        }
    }

    @Test
    fun binarySearchTree_remove_oneSubTreeNode_equalsTrue() {
        val result = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(50)
            enqueue(30)
            enqueue(10)
            enqueue(45)
            enqueue(35)
            enqueue(70)
            enqueue(60)
            enqueue(59)
            enqueue(100)
            enqueue(85)
            enqueue(105)
        }
        // 단말 노드 삭제
        binarySearchTree.remove(20)

        // 전체 데이터 검사
        binarySearchTree.preOrderTraversal {
            assertEquals(result.dequeue(), it.key)
        }
    }

    @Test
    fun binarySearchTree_remove_twoSubTreeNode_equalsTrue() {
        val result = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(50)
            enqueue(30)
            enqueue(20)
            enqueue(10)
            enqueue(45)
            enqueue(35)
            enqueue(70)
            enqueue(60)
            enqueue(59)
            enqueue(105)
            enqueue(85)
        }
        // 단말 노드 삭제
        binarySearchTree.remove(100)

        // 전체 데이터 검사
        binarySearchTree.preOrderTraversal {
            assertEquals(result.dequeue(), it.key)
        }
    }


    @Test
    fun binarySearchTree_remove_twoSubTreeNode_withLeftChild_equalsTrue() {
        val result = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(50)
            enqueue(35)
            enqueue(20)
            enqueue(10)
            enqueue(45)
            enqueue(70)
            enqueue(60)
            enqueue(59)
            enqueue(100)
            enqueue(85)
            enqueue(105)
        }
        // 단말 노드 삭제
        binarySearchTree.remove(30)

        // 전체 데이터 검사
        binarySearchTree.preOrderTraversal {
            assertEquals(result.dequeue(), it.key)
        }
    }

    @Test
    fun binarySearchTree_remove_twoSubTreeNode_withBothSideChild_equalsTrue() {
        val result = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(50)
            enqueue(30)
            enqueue(20)
            enqueue(10)
            enqueue(45)
            enqueue(35)
            enqueue(85)
            enqueue(60)
            enqueue(59)
            enqueue(100)
            enqueue(105)
        }
        // 단말 노드 삭제
        binarySearchTree.remove(70)

        // 전체 데이터 검사
        binarySearchTree.preOrderTraversal {
            assertEquals(result.dequeue(), it.key)
        }
    }


    @Test
    fun binarySearchTree_remove_twoSubTreeNode_withBothSideChild_rootNode_equalsTrue() {
        val result = GwQueueBasedOnArray<Int>(queueSize = dataSize).apply {
            enqueue(59)
            enqueue(30)
            enqueue(20)
            enqueue(10)
            enqueue(45)
            enqueue(35)
            enqueue(70)
            enqueue(60)
            enqueue(100)
            enqueue(85)
            enqueue(105)
        }
        // 단말 노드 삭제
        binarySearchTree.remove(50)

        // 전체 데이터 검사
        binarySearchTree.preOrderTraversal {
            assertEquals(result.dequeue(), it.key)
        }
    }
}