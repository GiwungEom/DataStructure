package com.gw.study.datastructure.sort

/**
 *  병합 정렬 (분할 정복)
 *  성능 평가 : n 로그 2의 n
 */
class MergeSort {

    fun sort(intArray: IntArray) {
        divideArray(intArray, 0, intArray.lastIndex)
    }

    private fun divideArray(intArray: IntArray, frontIndex: Int, rearIndex: Int) {
        if (frontIndex < rearIndex) {

            val midIndex = (frontIndex + rearIndex) / 2

            divideArray(intArray, frontIndex, midIndex)
            divideArray(intArray, midIndex + 1, rearIndex)

            mergeTwoArea(intArray, frontIndex, midIndex, rearIndex)
        }
    }

    private fun mergeTwoArea(intArray: IntArray, frontIndex: Int, midIndex: Int, rearIndex: Int) {

        val sortArray = IntArray(size = intArray.size)

        var fIdx = frontIndex
        var rIdx = midIndex + 1
        var sortArrayIndex = frontIndex

        while (fIdx <= midIndex && rIdx <= rearIndex) {
            if (intArray[fIdx] < intArray[rIdx]) {
                sortArray[sortArrayIndex++] = intArray[fIdx++]
            } else {
                sortArray[sortArrayIndex++] = intArray[rIdx++]
            }
        }

        if (fIdx > midIndex) {
            for (i in rIdx..rearIndex) {
                sortArray[sortArrayIndex++] = intArray[i]
            }
        } else {
            for (i in fIdx..midIndex) {
                sortArray[sortArrayIndex++] = intArray[i]
            }
        }

        for (i in frontIndex..rearIndex) {
            intArray[i] = sortArray[i]
        }
    }
}