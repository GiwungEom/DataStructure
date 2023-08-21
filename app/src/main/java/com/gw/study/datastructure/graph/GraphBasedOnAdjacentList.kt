package com.gw.study.datastructure.graph

import com.gw.study.datastructure.list.GwLinkedList
import com.gw.study.datastructure.stack.GwStackBasedOnArray

/**
 *  인접 리스트 기반의 그래프
 *  무방향 그래프
 *  정점(Vertex)는 Int 형
 */
class GraphBasedOnAdjacentList(
    private val vertexNum: Int
) {

    var numberOfEdge = 0
        private set
    private val array: Array<GwLinkedList<Vertex>> = Array(size = vertexNum) { GwLinkedList { num1, num2 -> num1.ordinal - num2.ordinal } }

    fun addEdge(fromVertex: Vertex, toVertex: Vertex) {
        array[fromVertex.ordinal].insert(toVertex)
        array[toVertex.ordinal].insert(fromVertex)
        numberOfEdge++
    }

    fun showGraphEdgeInfo(action: (vertexInfo: Vertex, edgeInfo: Vertex) -> Unit) {
        array.forEachIndexed { index, edgeList ->
            edgeList.first()?.let { action(Vertex.values()[index], it) }
            while(true) {
                edgeList.next()?.let { action(Vertex.values()[index], it) } ?: break
            }
        }
    }

    // 깊이 우선 탐색
    fun visitDfsGraphVertex(startVertex: Vertex, action: (vertex: Vertex) -> Unit) {
        // 정점 방문 여부 체크
        val vertexVisitCheck: Array<Vertex?> = Array(size = vertexNum) { null }
        // 정점 방문 히스토리
        val visitHistory: GwStackBasedOnArray<Vertex> = GwStackBasedOnArray(vertexNum)

        var vertex = startVertex
        var currentVertex = vertex
        vertexVisitCheck.add(vertex).also { action(vertex) }
        visitHistory.push(vertex)

        while (array[vertex.ordinal].first()?.also { currentVertex = it } != null) {
            if (vertexVisitCheck.find { it == currentVertex } == null) { // 방문 기록 없음
                vertexVisitCheck.add(currentVertex)
                visitHistory.push(currentVertex)
                vertex = currentVertex
                action(vertex)

            } else { // 방문 기록 존재
                var foundNewVertex = false
                while (array[vertex.ordinal].next()?.also { currentVertex = it } != null) {
                    if (vertexVisitCheck.find { it == currentVertex } == null) {
                        vertexVisitCheck.add(currentVertex)
                        visitHistory.push(currentVertex)
                        vertex = currentVertex
                        foundNewVertex = true
                        action(vertex)
                        break
                    }
                }

                // 정점의 간선을 탐색하였고, 새로운 정점을 찾지 못한 상태
                if (!foundNewVertex) {
                    // 정점의 간선을 다 돌았으면 히스토리에서 이전 정점으로 pop 한다.
                    val prevVertex = visitHistory.pop()
                    if (prevVertex != null) {
                        vertex = prevVertex
                    } else {
                        // 히스토리가 존재 하지 않는다면, 모든 정점을 탐색한 상태.
                        break
                    }
                }
            }
        }
    }

    private fun Array<Vertex?>.add(vertex: Vertex) {
        var index = 0
        while (index < this.size) {
            if (this[index] == null) {
                this[index] = vertex
                break
            }
            index++
        }
    }
}