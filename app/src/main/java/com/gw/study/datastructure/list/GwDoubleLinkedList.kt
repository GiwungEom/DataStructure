package com.gw.study.datastructure.list

/**
 * 양방향 연결 더미 리스트
 * head, tail 에 더미 노드 존재.
 * tail 에 노드 추가
 */
class GwDoubleLinkedList<T> {

    private data class Node<P>(
        val data: P? = null,
        var prev: Node<P>? = null,
        var next: Node<P>? = null
    )

    private val head: Node<T> = Node() // 더미 노드 추가
    private val tail: Node<T> = Node() // 더미 노드 추가
    private var cur: Node<T>? = null

    var numberOfData = 0
        private set

    init {
        head.next = tail
        tail.prev = head
    }

    fun insert(data: T) {
        val newNode = Node(data)
        tail.prev?.next = newNode
        newNode.prev = tail.prev
        newNode.next = tail
        tail.prev = newNode
        numberOfData++
    }
    // D - O - O - D
    fun first(): T? {
        cur = head.next
        return cur?.data
    }

    fun next(): T? {
        cur = cur?.next
        return cur?.data
    }

    fun prev(): T? {
        cur = cur?.prev
        return cur?.data
    }

    // 참조가 이뤄진 노드를 삭제 한다.
    fun remove(): T? {
        val delNode = cur
        delNode?.prev?.next = delNode?.next
        delNode?.next?.prev = delNode?.prev
        cur = cur?.prev
        numberOfData--
        return delNode?.data
    }

}