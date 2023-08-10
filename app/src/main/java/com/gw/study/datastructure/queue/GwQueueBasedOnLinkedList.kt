package com.gw.study.datastructure.queue

/**
 *  연결 리스트 기반 큐
 *  front 에서 dequeue, rear 에 enqueue
 */
class GwQueueBasedOnLinkedList<T> : GwQueue<T> {

    private data class Node<R>(val data: R?, var next: Node<R>? = null)

    private var front: Node<T>? = null
    private var rear: Node<T>? = null

    override var numberOfData: Int = 0
        private set

    override fun enqueue(data: T) {
        val newNode = Node(data = data)
        if (isEmpty()) {
            front = newNode
            rear = newNode
        } else {
            rear?.next = newNode
            rear = newNode
        }
        numberOfData++
    }

    override fun dequeue(): T {
        if (isEmpty()) {
            throw IllegalStateException("Queue is Empty")
        }
        val delNode = front
        front = front?.next
        delNode?.next = null
        return requireNotNull(delNode?.data)
    }

    override fun peek(): T {
        return requireNotNull(front?.data)
    }

    override fun isEmpty() = front == null
}