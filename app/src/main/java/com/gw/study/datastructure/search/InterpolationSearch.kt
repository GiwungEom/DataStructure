package com.gw.study.datastructure.search

/**
 *  보간 탐색
 *  이진 탐색의 보완 버전
 *  데이터와 인덱스와 비례 한다 가정 하며 첫 시작 지점을 찾는다.
 *
 *  <----------------- A ---------------->
 *  <-------- Q ------->
 *  --------------------------------------
 *  low                s               high
 *
 *  데이터 비례
 *  A = arr[high] - arr[low], Q = arr[s] - arr[low]
 *
 *  인덱스 비례
 *  A : Q = (high - low) : (s - low)
 *  => A(s-low) == Q(high-low)
 *  => s = Q(high-low)/A + low
 *
 *             x - arr[low]
 *  => s =  ------------------(high-low) + low
 *          arr[high]-arr[low]
 *
 *  찾는 값 x 를 대입 하여 탐색위치 s 를 구하는 식
 */
class InterpolationSearch {

    fun search(array: IntArray, data: Int, first: Int, last: Int): Int? {

        return if (array[first] <= data && array[last] >= data) {
            val mid = (((data - array[first]).toDouble() / (array[last] - array[first]) * (last - first)) + first).toInt()

            if (array[mid] == data) {
                array[mid]
            } else if (array[mid] > data) {
                search(array, data, first, mid - 1)
            } else {
                search(array, data, mid + 1, last)
            }
        } else {
            null
        }
    }

}