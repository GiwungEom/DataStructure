package com.gw.study.datastructure.queue

import java.lang.IllegalStateException

/**
 *  배열 기반 원형 큐
 *  배열 1개의 인덱스를 데이터 공간 확인 용도로 사용한다.
 *  enqueue, dequeue 시 front, rear 인덱스 한칸 이동 후 삽입/삭제 진행.
 */
class GwQueueBasedOnArray<T>(
    private val queueSize: Int = 10
) : GwQueue<T> {

    private var front: Int = 0
    private var rear: Int = 0
    private val array: Array<Any?> = Array(size = queueSize) { null }

    override var numberOfData: Int = 0
        private set

    override fun isEmpty(): Boolean = front == rear

    private fun isFull(): Boolean = getNextPos(rear) == front

    private fun getNextPos(pos: Int): Int {
        return if (pos == queueSize - 1) 0
            else pos + 1
    }

    override fun enqueue(data: T) {
        if (isFull()) {
            throw IllegalStateException("Queue is full")
        }
        rear = getNextPos(rear)
        array[rear] = data
        numberOfData++
    }

    @Suppress("UNCHECKED_CAST")
    override fun dequeue(): T {
        if (isEmpty()) {
            throw IllegalStateException("Queue is Empty")
        }

        numberOfData--
        front = getNextPos(front)
        return requireNotNull(array[front]) as T
    }

    @Suppress("UNCHECKED_CAST")
    override fun peek(): T = requireNotNull(array[getNextPos(front)]) as T
}