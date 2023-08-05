package com.gw.study.datastructure.search

/**
 *  이진 탐색
 *  배열의 중앙 값에서 첫번째 탐색 시도
 *  시간 복잡도 T(n) = log 2의 n
 */
class BinarySearch<T>(
    private val compareRule: (T, T) -> Int
) {

    // 루프 탐색 방식
    fun search(array: Array<T>, target: T): T? {
        var start = 0
        var last = array.lastIndex
        val getMid = { s:Int, e: Int -> (s + e) / 2 }

        while (start <= last) {
            val mid = getMid(start, last)

            // 탐색 값과 찾는 값이 같은 경우
            if (compareRule(array[mid], target) == 0) {
                return array[mid]
            } else if (compareRule(array[mid], target) > 0) { // 탐색 값이 더 큰 경우
                last = mid - 1
            } else { // 탐색 값이 더 작은 경우
                start = mid + 1
            }
        }
        return null
    }

}