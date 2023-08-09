package com.gw.study.datastructure.stack

import com.gw.study.datastructure.list.GwCircularLinkedList

/**
 *  원형 연결 리스트 기반 [com.gw.study.datastructure.list.GwCircularLinkedList] 스택
 *  원현 연결 리스트 head 삽입/삭제
 *
 *  데이터 삽입
 *  ==> 1 - 2 - 3 - 4
 *
 *  연결 리스트 내부 순서
 *  H
 *  4 - 3 - 2 - 1
 * 삽입/삭제
 */
class GwStackBasedOnCircularLinkedList<T> : GwStack<T> {

    private val linkedList: GwCircularLinkedList<T> = GwCircularLinkedList()

    override var numberOfData: Int = 0
        get() = linkedList.numberOfCount
        private set

    override fun push(data: T) = linkedList.insertFront(data)

    override fun pop(): T? = with(linkedList) {
        first()
        remove()
    }

    override fun peek(): T? = linkedList.first()

    override fun empty(): Boolean = linkedList.numberOfCount == 0

}