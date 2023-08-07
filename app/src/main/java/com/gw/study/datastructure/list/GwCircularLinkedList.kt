package com.gw.study.datastructure.list

/**
 * 원형 연결 리스트
 */
class GwCircularLinkedList<T> {

    private data class Node<P>(val data: P?, var next: Node<P>? = null)

    private var tail: Node<T>? = null
    private var cur: Node<T>? = null
    private var before: Node<T>? = null
    var numberOfCount: Int = 0
        private set

    fun insertFront(data: T) {
        val newNode = Node(data)

        if (tail == null) {
            newNode.next = newNode
            tail = newNode
        } else {
            newNode.next = tail?.next
            tail?.next = newNode
        }
        numberOfCount++
    }

    fun insertBackend(data: T) {
        val newNode = Node(data)
        if (tail == null) {
            newNode.next = newNode
            tail = newNode
        } else {
            newNode.next = tail?.next
            tail?.next = newNode
        }
        tail = newNode
        numberOfCount++
    }

    fun first(): T? {
        cur = tail?.next
        before = tail
        return cur?.data
    }

    fun next(): T? {
        before = cur
        cur = cur?.next
        return cur?.data
    }

    fun remove(): T? {
        val delNode = cur

        if (delNode == tail) {
            tail = if (tail?.next == null) {
                null
            } else {
                before
            }
        }
        before?.next = cur?.next
        numberOfCount--
        return delNode?.data
    }

}