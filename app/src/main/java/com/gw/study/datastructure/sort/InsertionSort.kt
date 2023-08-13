package com.gw.study.datastructure.sort

/**
 *  삽입 정렬
 *  성능 평가 : n의 제곱
 */
class InsertionSort {

    fun sort(intArray: IntArray) {
        for (index in 1 until intArray.size) {
            val targetData = intArray[index]
            var targetPosition = 0
            for (innerIndex in index - 1 downTo 0) {
                // innerIndex 데이터 가 타겟 데이터 보다 우선 순위가 낮은 경우
                if (intArray[innerIndex] > targetData) {
                    intArray[innerIndex + 1] = intArray[innerIndex]
                } else {
                    break
                }
                targetPosition = innerIndex
            }

            intArray[targetPosition] = targetData
        }
    }

}