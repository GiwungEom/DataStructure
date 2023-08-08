package com.gw.study.datastructure.stack

/**
 * 연결 리스트 기반 스택
 * 연결 리스트 - Head 에서 Node 추가/삭제 진행
 */
class GwStackBasedOnLinkedList<T> : GwStack<T> {

    data class Node<P>(val data: P, var next: Node<P>? = null)

    private var head: Node<T>? = null

    override var numberOfData: Int = 0
        private set

    override fun empty(): Boolean = head == null

    override fun pop(): T? {
        val delNode = head
        head = head?.next
        delNode?.next = null
        return delNode?.data?.also { --numberOfData }
    }

    override fun push(data: T) {
        val newNode = Node(data = data)
        newNode.next = head
        head = newNode
        ++numberOfData
    }

    override fun peek(): T? = head?.data
}