package com.gw.study.datastructure.heap

/**
 *  배열 기반 힙 트리
 */
class GwHeap<T>(
    heapSize: Int = 100,
    private val compare: (T, T) -> Int
) {
    private val array: Array<Any?> = Array(size = heapSize + 1) { null }

    var numberOfData = 0
        private set

    private val rootPosition = 1
    private val lastPosition
        get() = numberOfData + rootPosition

    // 완전 이진 트리 마지막 자리에 data 추가 후
    // 부모 노드와 우선 순위 비교 하며 자리 찾는 방식
    // 하지만 실제 소스는 마지막 자리에 data 를 추가 하지 않고
    // 인덱스 만 가지고 비교 결과 후 data 삽입 한다.
    @Suppress("UNCHECKED_CAST")
    fun insert(data: T) {
        var currentIndex: Int = lastPosition

        while (currentIndex != rootPosition) {
            // 추가 데이터 우선 순위가 높을때 까지 동작
            if (compare(data, array[currentIndex.parentIdx()] as T) < 0) {
                break
            }
            array[currentIndex] = array[currentIndex.parentIdx()]
            currentIndex = currentIndex.parentIdx()
        }
        array[currentIndex] = data
        numberOfData++
    }

    // 루트 노드 삭제 후
    // 가장 마지막 노드를 루트에 위치 한 뒤
    // 자식 노드와 우선 순위 비교 하며 자리 찾아감
    @Suppress("UNCHECKED_CAST")
    fun delete(): T {
        val delData = array[rootPosition] as T
        val lastData = requireNotNull(array[numberOfData] as T)

        var currentPosition = rootPosition
        // 마지막 노드와 현재 위치 자식들 중 우선 순위가 높은 노드 보다 작을때 까지 동작
        while (currentPosition.getPriorChildIndex() != 0) {
            val priorChildIndex = currentPosition.getPriorChildIndex()

            if (compare(lastData, array[priorChildIndex] as T) >= 0) break
            array[currentPosition] = array[priorChildIndex]
            currentPosition = priorChildIndex
        }

        array[currentPosition] = lastData
        numberOfData--
        return delData
    }

    @Suppress("UNCHECKED_CAST")
    fun getData(index: Int): T? = array[index] as? T

    @Suppress("UNCHECKED_CAST")
    private fun Int.getPriorChildIndex(): Int =
        if (leftChildIdx() > numberOfData) {
            0
        } else if (leftChildIdx() == numberOfData) {
            leftChildIdx()
        } else {
            if (compare(
                    array[leftChildIdx()] as T,
                    array[rightChildIdx()] as T
                ) >= 0) {
                leftChildIdx()
            } else {
                rightChildIdx()
            }
        }

    private fun Int.leftChildIdx(): Int = this * 2
    private fun Int.rightChildIdx(): Int = this * 2 + 1
    private fun Int.parentIdx(): Int = this / 2

}