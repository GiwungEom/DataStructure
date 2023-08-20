package com.gw.study.datastructure.graph

import com.gw.study.datastructure.list.GwLinkedList

/**
 *  인접 리스트 기반의 그래프
 *  무방향 그래프
 *  정점(Vertex)는 Int 형
 */
class GraphBasedOnAdjacentList(
    vertexNum: Int
) {

    var numberOfEdge = 0
        private set
    private val array: Array<GwLinkedList<Int>> = Array(size = vertexNum) { GwLinkedList() }

    fun addEdge(fromVertex: Int, toVertex: Int) {
        array[fromVertex].insert(toVertex)
        array[toVertex].insert(fromVertex)
        numberOfEdge++
    }

    fun showGraphEdgeInfo(action: (vertexInfo: Int, edgeInfo: Int) -> Unit) {
        array.forEachIndexed { index, edgeList ->
            edgeList.first()?.let { action(index, it) }
            while(true) {
                edgeList.next()?.let { action(index, it) } ?: break
            }
        }
    }
}