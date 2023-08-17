package com.gw.study.datastructure.search

import com.gw.study.datastructure.tree.GwBinaryTree

/**
 *  보완된 이진 탐색 트리.
 *  균형 인수 절대값 2 이상일 경우 트리의 높이를 재조정 한다.
 *  좌측 자식 존재 할 경우 +1 균형 인수, 우측 자식 존재 할 경우 -1 균형 인수로 적용
 */
class AvlTree(
    private val binarySearchTree: BinarySearchTree = BinarySearchTree()
) : IBinarySearchTree by binarySearchTree {

    // BST 삽입 후 재조정
    override fun insert(key: Int, data: Int) {
        binarySearchTree.insert(key, data)

        // 전체 노드에 대해 balance factor 체크
        binarySearchTree.rootNode = readjust(requireNotNull(binarySearchTree.rootNode))
    }

    // BST 삭제 후 재조정
    override fun remove(key: Int): Int? {
        val delData = binarySearchTree.remove(key)

        // 전체 노드에 대해 balance factor 체크
        binarySearchTree.rootNode = readjust(requireNotNull(binarySearchTree.rootNode))
        return delData
    }

    // 트리 높이 재조정
    private fun readjust(node: GwBinaryTree<Slot>?): GwBinaryTree<Slot>? {

        if (node == null) return null

        node.leftTree = readjust(node.leftTree)
        node.rightTree = readjust(node.rightTree)

        val heightDiff = getHeightDiff(node)
        if (heightDiff > 1) {   // 좌측 트리가 더 높은 경우
            return if (getHeightDiff(node.leftTree) > 0) { // LL, LR 상태 체크
                // LL 상태
                rotateLL(node)
            } else {
                // LR 상태
                rotateLR(node)
            }
        }

        if (heightDiff < -1) {  // 우측 트리가 더 높은 경우
            return if (getHeightDiff(node.rightTree) < 0) { // RR, RL 상태 체크
                // RR 상태
                rotateRR(node)
            } else {
                // RL 상태
                rotateRL(node)
            }
        }

        return node
    }

    private fun rotateLL(node: GwBinaryTree<Slot>): GwBinaryTree<Slot> {
        val cNode = requireNotNull(node.leftTree)
        node.leftTree = cNode.rightTree
        cNode.rightTree = node
        return cNode
    }

    private fun rotateRR(node: GwBinaryTree<Slot>): GwBinaryTree<Slot> {
        val cNode = requireNotNull(node.rightTree)
        node.rightTree = cNode.leftTree
        cNode.leftTree = node
        return cNode
    }

    private fun rotateLR(node: GwBinaryTree<Slot>): GwBinaryTree<Slot> {
        val cNode = requireNotNull(node.leftTree)
        node.leftTree = rotateRR(cNode)
        return rotateLL(node)
    }

    private fun rotateRL(node: GwBinaryTree<Slot>): GwBinaryTree<Slot> {
        val cNode = requireNotNull(node.rightTree)
        node.rightTree = rotateLL(cNode)
        return rotateRR(node)
    }

    // 노드 좌 우측 높이 차 계산
    private fun getHeightDiff(node: GwBinaryTree<Slot>?): Int {

        if (node == null) return 0

        val lHeight = getHeight(node.leftTree)
        val rHeight = getHeight(node.rightTree)

        return lHeight - rHeight
    }

    // 노드 높이 계산
    private fun getHeight(node: GwBinaryTree<Slot>?): Int {

        if (node == null) return 0

        val lHeight = getHeight(node.leftTree)
        val rHeight = getHeight(node.rightTree)

        return if (lHeight > rHeight) lHeight + 1 else rHeight + 1
    }
}