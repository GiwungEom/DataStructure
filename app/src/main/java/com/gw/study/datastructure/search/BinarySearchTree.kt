package com.gw.study.datastructure.search

import com.gw.study.datastructure.tree.GwBinaryTree
import com.gw.study.datastructure.tree.GwBinaryTreeTraversal

/**
 *  이진 탐색 트리
 *  부모 노드는 왼쪽 자식 노드 보다 크고, 오른쪽 자식노드 보다 작다.
 */

interface IBinarySearchTree {
    fun insert(key: Int, data: Int)

    fun search(key: Int): Int?

    fun remove(key: Int): Int?

    fun traversal(action: (Slot) -> Unit)
}

class BinarySearchTree(
    private val binaryTreeTraversal: GwBinaryTreeTraversal<Slot> = GwBinaryTreeTraversal()
) : IBinarySearchTree {

    var rootNode: GwBinaryTree<Slot>? = null

    override fun insert(key: Int, data: Int) {
        val newBTree = GwBinaryTree(Slot(key = key, data = data))


        var pNode = rootNode
        var cNode = rootNode

        // 키 중복 체크
        if (search(key) != null) throw IllegalStateException("Duplication Key $key")

        while (cNode != null) {
            pNode = cNode
            cNode = if (cNode.data.key > newBTree.data.key) {
                pNode.leftTree
            } else {
                pNode.rightTree
            }
        }

        if (pNode == null) {
            rootNode = newBTree
        } else {
            if (pNode.data.key > newBTree.data.key) {
                pNode.leftTree = newBTree
            } else {
                pNode.rightTree = newBTree
            }
        }
    }

    override fun search(key: Int): Int? {

        var cNode = rootNode

        while (cNode != null) {
            if (cNode.data.key == key) {
                return cNode.data.data
            }

            cNode = if (cNode.data.key > key) {
                cNode.leftTree
            } else {
                cNode.rightTree
            }
        }

        return null
    }

    override fun remove(key: Int): Int? {

        // 삭제 노드 찾기
        var delNode: GwBinaryTree<Slot>?

        val vRootNode = GwBinaryTree(Slot(0, 0))
        vRootNode.rightTree = rootNode

        var pNode = vRootNode
        var currentNode = pNode.rightTree

        while (currentNode != null && currentNode.data.key != key) {
            pNode = currentNode
            currentNode = if (currentNode.data.key > key) {
                currentNode.leftTree
            } else {
                currentNode.rightTree
            }
        }

        if (currentNode == null) return null
        delNode = currentNode

        // 1. 단말노드 삭제 -- 좌 우측에 아무 노드도 없는 경우
        // 2. 하나의 노드만 가지고 있을경우
        // 3. 둘다 가지고 있을 경우 - 우측 노드의 가장 작은 값을 대입.
        //                     3.1 가장 작은 값이 삭제 우측 자식 트리 인경우
        //                           우측 자식 데이터 값을 삭제 노드 데이터에 넣는다.
        //                           우측 자식 의 우측 노드를 삭제 노드의 우측 자식에 넣는다.
        //                     3.2 가장 작은 값이 삭제 우측 자식 트리의 좌측 자식 트리 인경우
        //                           가장 마지막 좌측 노드의 값을 삭제 데이터에 넣는다.
        //                           가장 마지막 좌측 노드의 우측 자식 노드들을 가장 마지막 좌측 노드의 부모 노드에 연결 한다.

        if (delNode.leftTree == null && delNode.rightTree == null) {
            if (pNode.leftTree === delNode) {
                pNode.leftTree = null
            } else {
                pNode.rightTree = null
            }
        } else if (delNode.leftTree == null || delNode.rightTree == null) {
            if (pNode.leftTree === delNode) {
                pNode.leftTree = if (delNode.leftTree != null) delNode.leftTree else delNode.rightTree
            } else {
                pNode.rightTree = if (delNode.leftTree != null) delNode.leftTree else delNode.rightTree
            }
        } else {
            val cDelNode = GwBinaryTree(Slot(key = delNode.data.key, data = delNode.data.data))
            var mNode = delNode.rightTree
            var mPNode = delNode

            while (mNode?.leftTree != null) {
                mPNode = mNode
                mNode = mNode.leftTree
            }

            delNode.data.data = requireNotNull(mNode?.data?.data)
            delNode.data.key = requireNotNull(mNode?.data?.key)

            // 가장 작은 값이 삭제 우측 자식 트리의 좌측 자식 트리 인경우
            if (mPNode?.leftTree === mNode) {
                mPNode?.leftTree = mNode?.rightTree

            } else {
                // 가장 작은 값이 삭제 우측 자식 트리 인경우
                mPNode?.rightTree = mNode?.rightTree
            }
            delNode = cDelNode
        }

        if (vRootNode.rightTree?.data != rootNode?.data) {
            rootNode = vRootNode.rightTree
        }

        return delNode.data.data
    }

    override fun traversal(action: (Slot) -> Unit) {
        preOrderTraversal(action)
    }

    private fun preOrderTraversal(action: (Slot) -> Unit) {
        binaryTreeTraversal.preorderTraversal(rootNode, action = action)
    }
}