package com.gw.study.datastructure.tree

/**
 *  이진 트리 순회
 *  전위, 중위, 후위 순회
 */
class GwBinaryTreeTraversal<T> {

    // 전위 순회
    fun preorderTraversal(binaryTree: GwBinaryTree<T>?, action: (T) -> Unit) {
        if (binaryTree == null) return

        action(binaryTree.data)
        preorderTraversal(binaryTree.leftTree, action)
        preorderTraversal(binaryTree.rightTree, action)
    }

    // 중위 순회
    fun inorderTraversal(binaryTree: GwBinaryTree<T>?, action: (T) -> Unit) {
        if (binaryTree == null) return

        inorderTraversal(binaryTree.leftTree, action)
        action(binaryTree.data)
        inorderTraversal(binaryTree.rightTree, action)
    }

    // 후위 순회
    fun postorderTraversal(binaryTree: GwBinaryTree<T>?, action: (T) -> Unit) {
        if (binaryTree == null) return

        postorderTraversal(binaryTree.leftTree, action)
        postorderTraversal(binaryTree.rightTree, action)
        action(binaryTree.data)
    }

}