package com.gw.study.datastructure.graph

import com.gw.study.datastructure.list.GwLinkedList
import com.gw.study.datastructure.queue.GwPriorityQueueBasedOnHeap
import com.gw.study.datastructure.stack.GwStackBasedOnArray


/**
 *  크루스칼 알고리즘을 이용한 최소 비용 신장 트리 (GraphBasedOnAdjacentList 변형 버전)
 *  무방향 그래프
 */
class SpanningTree(
    private val vertexNum: Int
) {

    var numberOfEdge = 0
        private set
    private val array: Array<GwLinkedList<GraphNode>> = Array(size = vertexNum) { GwLinkedList { node1, node2 -> node1.vertex.ordinal - node2.vertex.ordinal } }

    // 간선 가중치 내림차순 정렬
    private val priorityQueue: GwPriorityQueueBasedOnHeap<Edge> = GwPriorityQueueBasedOnHeap(heapSize = 100) { from, to -> from.weight - to.weight }

    fun addEdge(fromVertex: Vertex, toVertex: Vertex, weight: Int) {
        array[fromVertex.ordinal].insert(
            GraphNode(
                vertex = toVertex,
                edge = Edge(
                    fromVertex = fromVertex,
                    toVertex = toVertex,
                    weight = weight
                )
            )
        )
        array[toVertex.ordinal].insert(
            GraphNode(
                vertex = fromVertex,
                edge = Edge(
                    fromVertex = toVertex,
                    toVertex = fromVertex,
                    weight = weight
                )
            )
        )

        priorityQueue.enqueue(
            Edge(
                fromVertex = fromVertex,
                toVertex = toVertex,
                weight = weight
            )
        )

        numberOfEdge++
    }

    fun generateMstWithKruskalAlgorithm() {
        val recoveryEdge = GwLinkedList<Edge>()

        while (numberOfEdge >= vertexNum - 1) {

            val edge = priorityQueue.dequeue()
            removeEdge(edge)

            if (!isGraphConnected(edge.fromVertex)) {
                recoveryEdge.insert(edge)
            }
        }

        // 간선 복원
        if (recoveryEdge.numberOfData > 0) {
            recoveryEdge.first()?.let {
                recoveryEdge(it)
            }

            var edge: Edge? = null
            while (recoveryEdge.next()?.also { edge = it } != null) {
                edge?.let { recoveryEdge(it) }
            }
        }
    }

    private fun recoveryEdge(edge: Edge) {
        with(edge) {
            array[fromVertex.ordinal].insert(
                GraphNode(
                    vertex = toVertex,
                    edge = Edge(
                        fromVertex = fromVertex,
                        toVertex = toVertex,
                        weight = weight
                    )
                ))

            array[toVertex.ordinal].insert(
                GraphNode(
                    vertex = fromVertex,
                    edge = Edge(
                        fromVertex = toVertex,
                        toVertex = fromVertex,
                        weight = weight
                    )
                )
            )
        }
    }

    private fun removeEdge(edge: Edge) {
        with(edge) {
            removeWayEdge(fromVertex, toVertex)
            removeWayEdge(toVertex, fromVertex)
            numberOfEdge--
        }
    }

    private fun removeWayEdge(fromVertex: Vertex, toVertex: Vertex) {
        var currentVertex = fromVertex
        if (array[fromVertex.ordinal].first()?.also { currentVertex = it.vertex } != null) {
            if (currentVertex == toVertex) {
                array[fromVertex.ordinal].remove()
            }

            while (array[fromVertex.ordinal].next()?.also { currentVertex = it.vertex } != null) {
                if (currentVertex == toVertex) {
                    array[fromVertex.ordinal].remove()
                    break
                }
            }
        }
    }

    fun showGraphEdgeInfo(action: (vertexInfo: Vertex, edge: Edge) -> Unit) {
        array.forEachIndexed { index, edgeList ->
            edgeList.first()?.let { action(Vertex.values()[index], it.edge) }
            while(true) {
                edgeList.next()?.let { action(Vertex.values()[index], it.edge) } ?: break
            }
        }
    }

    // 깊이 우선 탐색을 활용한 그래프 연결 여부
    private fun isGraphConnected(fromVertex: Vertex): Boolean {
        // 정점 방문 여부 체크
        val vertexVisitCheck: Array<Vertex?> = Array(size = vertexNum) { null }
        // 정점 방문 히스토리
        val visitHistory: GwStackBasedOnArray<Vertex> = GwStackBasedOnArray(vertexNum)

        var vertex = fromVertex
        var currentVertex = vertex
        vertexVisitCheck.add(vertex)
        visitHistory.push(vertex)

        while (array[vertex.ordinal].first()?.also { currentVertex = it.vertex } != null) {
            if (currentVertex == fromVertex) return true

            if (vertexVisitCheck.find { it == currentVertex } == null) { // 방문 기록 없음
                vertexVisitCheck.add(currentVertex)
                visitHistory.push(currentVertex)
                vertex = currentVertex

            } else { // 방문 기록 존재
                var foundNewVertex = false
                while (array[vertex.ordinal].next()?.also { currentVertex = it.vertex } != null) {
                    if (currentVertex == fromVertex) return true

                    if (vertexVisitCheck.find { it == currentVertex } == null) {
                        vertexVisitCheck.add(currentVertex)
                        visitHistory.push(currentVertex)
                        vertex = currentVertex
                        foundNewVertex = true
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
        return false
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