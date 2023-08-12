package com.gw.study.datastructure.sort

/**
 *  버블 정렬
 *  인접한 두개의 데이터 오름차순 정렬
 *  성능 평가 : n의 제곱
 */
class BubbleSort {

    fun sort(intArray: IntArray) {
        intArray.forEachIndexed { outerIndex, _ ->
            (0 until intArray.size - outerIndex - 1).forEachIndexed { index, _ ->
                if (intArray[index] > intArray[index+1]) {
                    val temp = intArray[index]
                    intArray[index] = intArray[index+1]
                    intArray[index+1] = temp
                }
            }
        }
    }
}