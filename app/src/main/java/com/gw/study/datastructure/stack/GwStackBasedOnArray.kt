package com.gw.study.datastructure.stack

/**
 * 배열 기반 스택
 */
class GwStackBasedOnArray<T>(
    private val stackSize: Int = 10
) : GwStack<T> {

    private val array: Array<Any?> = Array(size = stackSize) { null }

    private var topIndex: Int = -1

    override val numberOfData: Int
        get() = topIndex + 1

    @Suppress("UNCHECKED_CAST")
    override fun pop(): T? {
        if (empty()) return null

        return array[topIndex--] as? T
    }

    override fun push(data: T) {
        if (topIndex >= stackSize) throw StackOverflowError()
        array[++topIndex] = data
    }

    @Suppress("UNCHECKED_CAST")
    override fun peek(): T? {
        if (empty()) return null
        return array[topIndex] as? T
    }

    override fun empty(): Boolean = topIndex == -1
}