package com.gw.study.datastructure.list

import org.jetbrains.annotations.TestOnly

/**
 *  Head 에 Dummy 노드 연결.
 *  삽입 시 Dummy 노드로 인한 동일한 로직 구성 가능
 *  @param compare 정렬 삽입 함수
 */
class GwLinkedList<T>(
    private val compare: ((T, T) -> Int)? = null,
) : GwList<T> {

    private data class Node<T>(var data: T?, var next: Node<T>? = null)

    private var head: Node<T> = Node(null, null) // dummy
    private var cur: Node<T>? = null
    private var before: Node<T>? = null

    override var numberOfData: Int = 0
        private set

    override fun insert(data: T): Boolean =
        if (compare == null) {
            insertWithoutOrder(data)
        } else {
            insertWithOrder(data)
        }

    private fun insertWithoutOrder(data: T): Boolean {
        val newNode = Node(data, null)
        newNode.next = head.next
        head.next = newNode
        return true.also { numberOfData++ }
    }

    private fun insertWithOrder(data: T): Boolean {
        val newNode = Node(data)
        var node: Node<T>? = head

        while (node?.next != null && requireNotNull(compare)(requireNotNull(node.next?.data), requireNotNull(newNode.data)) < 0) {
            // node.next 가 newNode 보다 우선 순위가 높은 경우
            node = node.next
        }
        newNode.next = node?.next
        node?.next = newNode
        return true.also { numberOfData++ }
    }

    override fun first(): T? {
        cur = head.next
        before = head
        return cur?.data
    }

    override fun next(): T? {
        if (cur?.next == null) return null
        before = cur
        cur = cur?.next
        return cur?.data
    }

    override fun remove(): T? {
        val removeData = cur
        before?.next = cur?.next
        cur = before
        return removeData?.data?.also { numberOfData-- }
    }

    @TestOnly
    override fun getData(i: Int): T? {
        TODO()
    }
}