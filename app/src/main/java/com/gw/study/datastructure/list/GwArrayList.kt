package com.gw.study.datastructure.list

import org.jetbrains.annotations.TestOnly

class GwArrayList<T>(
    private val size: Int = 10
) : GwList<T> {

    override var numberOfData: Int = 0
        private set

    private val array: Array<Any?> = Array(size = size) { null }
    private var curPosition = -1

    override fun insert(data: T): Boolean {
        if (numberOfData >= size) return false
        array[numberOfData++] = data
        return true
    }

    @Suppress("UNCHECKED_CAST")
    override fun first(): T? {
        if (numberOfData == 0) return null

        curPosition = 0
        return array[curPosition] as? T
    }

    @Suppress("UNCHECKED_CAST")
    override fun next(): T? {
        if (curPosition >= numberOfData - 1) return null

        return array[++curPosition] as? T
    }

    @Suppress("UNCHECKED_CAST")
    override fun remove(): T? {
        if (curPosition >= numberOfData) return null
        val removedData = array[curPosition]
        for (index in curPosition until array.lastIndex) {
            array[index] = array[index + 1]
        }
        numberOfData--
        curPosition--
        return removedData as? T
    }

    @Suppress("UNCHECKED_CAST")
    @TestOnly
    override fun getData(i: Int): T? = array[i] as? T
}