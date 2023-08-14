package com.gw.study.datastructure.sort

/**
 *  퀵 정렬
 *  left 를 pivot 으로 적용 함.
 *  성능 평가 : n 로그 2의 n
 */
class QuickSort {

    fun sort(intArray: IntArray) {
        quickSort(intArray, 0, intArray.lastIndex)
    }

    private fun quickSort(intArray: IntArray, left: Int, right: Int) {
        if (left <= right) {
            val mid = partition(intArray, left, right)
            quickSort(intArray, left, mid - 1)
            quickSort(intArray, mid + 1, right)
        }
    }

    // left 가 pivot 이 된다.
    private fun partition(intArray: IntArray, left: Int, right: Int): Int {
        var low = left + 1
        var high = right

        // low 와 high 가 교차 되기 전까지 동작
        while (low <= high) {
            while (low <= right && intArray[left] >= intArray[low]) {
                low++
            }

            while (high >= left + 1 && intArray[left] <= intArray[high]) {
                high--
            }

            if (low <= high) {
                swap(intArray, low, high)
            }
        }
        swap(intArray, left, high)
        return high
    }

    private fun swap(intArray: IntArray, low: Int, high: Int) {
        val temp = intArray[low]
        intArray[low] = intArray[high]
        intArray[high] = temp
    }

}