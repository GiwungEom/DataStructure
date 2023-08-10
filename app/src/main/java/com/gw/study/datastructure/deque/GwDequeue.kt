package com.gw.study.datastructure.deque

class GwDequeue<T> {

    data class Node<R>(
        val data: R,
        var prev: Node<R>? = null,
        var next: Node<R>? = null
    )

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    var numberOfData = 0
        private set

    fun addFirst(data: T) {
        val newNode = Node(data = data)
        if (head == null) {
            tail = newNode
        } else {
            newNode.next = head
            head?.prev = newNode
        }
        head = newNode
        numberOfData++
    }

    fun addLast(data: T) {
        val newNode = Node(data = data)
        if (tail == null) {
            head = newNode
        } else {
            tail?.next = newNode
            newNode.prev = tail
        }
        tail = newNode
        numberOfData++
    }

    fun removeFirst(): T {
        val delNode = head
        head = head?.next
        head?.prev = null
        delNode?.next = null
        numberOfData--
        return requireNotNull(delNode?.data)
    }

    fun removeLast(): T {
        val delNode = tail
        tail = tail?.prev
        tail?.next = null
        delNode?.prev = null
        numberOfData--
        return requireNotNull(delNode?.data)
    }
}