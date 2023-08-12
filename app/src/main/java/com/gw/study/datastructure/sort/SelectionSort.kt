package com.gw.study.datastructure.sort

/**
 *  개선된 선택 정렬
 *  성능 평가 : n의 제곱
 */
class SelectionSort {

    fun sort(intArray: IntArray) {
        intArray.forEachIndexed { outerIndex, outerData ->
            var targetIndex: Int = outerIndex
            var targetData: Int = outerData

            for (innerIndex in outerIndex + 1  until intArray.size) {
                val innerData = intArray[innerIndex]

                if (innerData < targetData) {
                    targetIndex = innerIndex
                    targetData = innerData
                }
            }

            val temp = intArray[outerIndex]
            intArray[outerIndex] = intArray[targetIndex]
            intArray[targetIndex] = temp
        }
    }
}