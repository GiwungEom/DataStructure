package com.gw.study.datastructure.graph

import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GraphBasedOnAdjacentListTest {

    @Test
    fun graph_addEdge_equalsTrue() {
        val graph = GraphBasedOnAdjacentList(5)
        repeat(5) {
            graph.addEdge(0, it)
        }
        assertEquals(5, graph.numberOfEdge)
    }

    @Test
    fun graph_showInfo_equalsTrue() {
        val vertexCount = 5
        // Pair<Vertex, Edge>
        val result = GwQueueBasedOnLinkedList<Pair<Int, Int>>().apply {
            repeat(vertexCount - 1) {
                enqueue(0 to (vertexCount - 1) - it)
            }
            repeat(vertexCount - 1) {
                enqueue(it + 1 to 0)
            }
        }

        val graph = GraphBasedOnAdjacentList(vertexCount)
        repeat(vertexCount - 1) {
            graph.addEdge(0, it + 1)
        }
        graph.showGraphEdgeInfo { vertexInfo, edgeInfo ->
            val resultPair = result.dequeue()
            assertEquals(resultPair.first, vertexInfo)
            assertEquals(resultPair.second, edgeInfo)
        }
    }
}