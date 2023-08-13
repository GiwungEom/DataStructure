package com.gw.study.datastructure.sort

import com.gw.study.datastructure.heap.GwHeap

/**
 *  힙 정렬
 *  성능 평가 : n 로그 2의 n
 */

class HeapSort {
    fun sort(intArray: IntArray) {
        val heap: GwHeap<Int> = GwHeap(heapSize = intArray.size) { num1, num2 -> num2 - num1 }
        intArray.forEach { heap.insert(it) }
        repeat(intArray.size) {
            intArray[it] = heap.delete()
        }
    }
}