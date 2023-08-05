package com.gw.study.datastructure.search

/**
 *  순차 탐색
 *  시간 복잡도 T(n) = n
 */
class LinearSearch<T> {

    fun search(array: Array<T>, target: T): T? {
        array.forEach {
            // 핵심 비교 연산.
            if (it == target) return it
        }
        return null
    }
}