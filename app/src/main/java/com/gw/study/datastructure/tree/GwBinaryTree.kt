package com.gw.study.datastructure.tree

/**
 *  이진 트리
 *  트리 그 자체가 이진 트리가 된다.
 *  좌 우측 노드가 null 일 경우에는 공집합이 존재 한다.
 */
class GwBinaryTree<T>(
    var data: T
) {

    var leftTree: GwBinaryTree<T>? = null
    var rightTree: GwBinaryTree<T>? = null

}