package com.gw.study.datastructure.graph

import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GraphBasedOnAdjacentListTest {

    @Test
    fun graph_addEdge_equalsTrue() {
        val graph = GraphBasedOnAdjacentList(5)
        repeat(5) {
            graph.addEdge(Vertex.A, Vertex.values()[it])
        }
        assertEquals(5, graph.numberOfEdge)
    }

    @Test
    fun graph_showInfo_equalsTrue() {
        val vertexCount = 5
        // Pair<Vertex, Edge>
        val result = GwQueueBasedOnLinkedList<Pair<Vertex, Vertex>>().apply {
            repeat(vertexCount - 1) {
                enqueue(Vertex.A to Vertex.values()[it + 1])
            }
            repeat(vertexCount - 1) {
                enqueue(Vertex.values()[it + 1] to Vertex.A)
            }
        }

        val graph = GraphBasedOnAdjacentList(vertexCount)
        repeat(vertexCount - 1) {
            graph.addEdge(Vertex.A, Vertex.values()[it + 1])
        }
        graph.showGraphEdgeInfo { vertexInfo, edgeInfo ->
            println("vertexInfo : $vertexInfo, edgeInfo : $edgeInfo")
            val resultPair = result.dequeue()
            assertEquals(resultPair.first, vertexInfo)
            assertEquals(resultPair.second, edgeInfo)
        }
    }

    @Test
    fun graph_dfsVisit_equalsTrue() {
        val vertexNumber = Vertex.values().size
        val resultList = listOf(
            GwQueueBasedOnLinkedList<Vertex>().apply {
                repeat(7) {
                    enqueue(Vertex.values()[it])
                }
            },
            GwQueueBasedOnLinkedList<Vertex>().apply {
                enqueue(Vertex.C)
                enqueue(Vertex.B)
                enqueue(Vertex.A)
                enqueue(Vertex.D)
                enqueue(Vertex.E)
                enqueue(Vertex.F)
                enqueue(Vertex.G)
            }
        )

        val graph = GraphBasedOnAdjacentList(vertexNumber)

        graph.addEdge(Vertex.A, Vertex.B)
        graph.addEdge(Vertex.A, Vertex.D)
        graph.addEdge(Vertex.B, Vertex.C)
        graph.addEdge(Vertex.D, Vertex.C)
        graph.addEdge(Vertex.D, Vertex.E)
        graph.addEdge(Vertex.E, Vertex.F)
        graph.addEdge(Vertex.E, Vertex.G)

        graph.visitDfsGraphVertex(Vertex.A) {
            assertEquals(resultList[0].dequeue(), it)
        }
        graph.visitDfsGraphVertex(Vertex.C) {
            assertEquals(resultList[1].dequeue(), it)
        }
    }
}