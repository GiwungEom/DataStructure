package com.gw.study.datastructure.tree

import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BinaryTreeTest {

    private fun generateTree(): GwBinaryTree<Int> =
        GwBinaryTree(0).apply {
            val binaryTreeLeft = GwBinaryTree(1)
            val binaryTreeRight = GwBinaryTree(2)
            val binaryTreeLeftLeft = GwBinaryTree(3)

            leftTree = binaryTreeLeft
            rightTree = binaryTreeRight
            binaryTreeLeft.leftTree = binaryTreeLeftLeft
        }

    @Test
    fun binaryTree_makeTree_equalsTrue() {
        val binaryTree = generateTree()

        assertEquals(0, binaryTree.data)
        assertEquals(1, binaryTree.leftTree?.data)
        assertEquals(2, binaryTree.rightTree?.data)
        assertEquals(3, binaryTree.leftTree?.leftTree?.data)
    }

    @Test
    fun binaryTree_traversal_equalsTrue() {

        // Traversal Result List
        val result = listOf(
            // Preorder
            GwQueueBasedOnLinkedList<Int>().apply {
                enqueue(0)
                enqueue(1)
                enqueue(3)
                enqueue(2)
            },
            // Postorder
            GwQueueBasedOnLinkedList<Int>().apply {
                enqueue(3)
                enqueue(1)
                enqueue(2)
                enqueue(0)
            },
            // Inorder
            GwQueueBasedOnLinkedList<Int>().apply {
                enqueue(3)
                enqueue(1)
                enqueue(0)
                enqueue(2)
            }
        )

        val binaryTree = generateTree()
        val bTreeTraversal = GwBinaryTreeTraversal<Int>()

        bTreeTraversal.preorderTraversal(binaryTree) { data: Int -> assertEquals(result[0/*preorder*/].dequeue(), data) }
        bTreeTraversal.postorderTraversal(binaryTree) { data: Int -> assertEquals(result[1/*postorder*/].dequeue(), data) }
        bTreeTraversal.inorderTraversal(binaryTree) { data: Int -> assertEquals(result[2/*inorder*/].dequeue(), data) }
    }

}