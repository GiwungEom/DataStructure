package com.gw.study.datastructure.queue

import com.gw.study.datastructure.heap.GwHeap

class GwPriorityQueueBasedOnHeap<T>(
    heapSize: Int = 100,
    compare: (T, T) -> Int
) : GwQueue<T> {

    private val heap: GwHeap<T> = GwHeap(heapSize = heapSize, compare = compare)

    override val numberOfData: Int
        get() = heap.numberOfData

    override fun enqueue(data: T) {
        heap.insert(data)
    }

    override fun dequeue(): T = heap.delete()

    override fun peek(): T = requireNotNull(heap.getData(1))

    override fun isEmpty() = heap.numberOfData == 0
}