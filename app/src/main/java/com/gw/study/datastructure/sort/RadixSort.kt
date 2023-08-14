package com.gw.study.datastructure.sort

import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList

/**
 *  기수 정렬
 *  성능 평가 : n
 */
class RadixSort {

    /**
     * @param maxLength 가장 큰 데이터 길이
     */
    fun sort(intArray: IntArray, radix: Int, maxLength: Int) {
        val radixArray = Array<GwQueueBasedOnLinkedList<Int>>(size = radix) { GwQueueBasedOnLinkedList() }

        var divFactor = 1
        repeat(maxLength) {

            // 각 자리수에 해당 하는 숫자의 위치에 삽입
            intArray.forEach {
                val number = (it / divFactor) % 10
                radixArray[number].enqueue(it)
            }

            // 데이터 추출 후 데이터 순차적 이동
            var index = 0
            radixArray.forEach {
                while (!it.isEmpty()) {
                    intArray[index++] = it.dequeue()
                }
            }

            divFactor *= 10
        }
    }
}