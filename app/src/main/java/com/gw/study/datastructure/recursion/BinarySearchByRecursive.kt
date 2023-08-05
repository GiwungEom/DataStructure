package com.gw.study.datastructure.recursion

/**
 * [com.gw.study.datastructure.search.BinarySearch] 재귀 버전
 */
class BinarySearchByRecursive<T>(
    private val compareRule: (T, T) -> Int
) {
    private val getMid = { s:Int, e: Int -> (s + e) / 2 }

    fun search(array: Array<T>, target: T, start: Int, last: Int): T? {
        if (start > last) return null
        val mid = getMid(start, last)

        return if (array[mid] == target) {
            array[mid]
        } else if (compareRule(array[mid], target) > 0) {
            search(array, target, start, mid - 1)
        } else {
            search(array, target, mid + 1, last)
        }
    }

}