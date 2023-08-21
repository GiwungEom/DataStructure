package com.gw.study.datastructure.graph

import com.gw.study.datastructure.list.GwLinkedList
import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SpanningTreeTest {

    private lateinit var spanningTree: SpanningTree

    @Before
    fun initialize() {
        spanningTree = SpanningTree(Vertex.values().size - 1).apply {
            addEdge(Vertex.A, Vertex.B, 9)
            addEdge(Vertex.B, Vertex.C, 2)
            addEdge(Vertex.A, Vertex.C, 12)
            addEdge(Vertex.A, Vertex.D, 8)
            addEdge(Vertex.D, Vertex.C, 6)
            addEdge(Vertex.A, Vertex.F, 11)
            addEdge(Vertex.F, Vertex.D, 4)
            addEdge(Vertex.D, Vertex.E, 3)
            addEdge(Vertex.E, Vertex.C, 7)
            addEdge(Vertex.F, Vertex.E, 13)
        }
    }

    @Test
    fun spanningTree_addEdge_equalsTrue() {
        assertEquals(10, spanningTree.numberOfEdge)
    }

    @Test
    fun spanningTree_kruskalMst_equalsTrue() {
        spanningTree.generateMstWithKruskalAlgorithm()

        val resultSpanningTree = SpanningTree(Vertex.values().size - 1).apply {
            addEdge(Vertex.D, Vertex.C, 6)
            addEdge(Vertex.B, Vertex.C, 2)
            addEdge(Vertex.A, Vertex.D, 8)
            addEdge(Vertex.F, Vertex.D, 4)
            addEdge(Vertex.D, Vertex.E, 3)
        }

        val resultArray = Array<GwLinkedList<GraphNode>>(size = Vertex.values().size) { GwLinkedList { node1, node2 -> node1.edge.toVertex.ordinal - node2.edge.toVertex.ordinal } }
        resultSpanningTree.showGraphEdgeInfo { vertexInfo, edge ->
            resultArray[vertexInfo.ordinal].insert(
                GraphNode(
                    vertex = vertexInfo,
                    edge = edge
                )
            )
        }

        val resultQueue = GwQueueBasedOnLinkedList<GraphNode>().apply {
            resultArray.forEachIndexed { _, linkedList ->
                var edge: GraphNode? = null
                linkedList.first()?.let { enqueue(it) }
                while (linkedList.next()?.also { edge = it } != null) {
                    edge?.let { enqueue(it) }
                }
            }
        }

        spanningTree.showGraphEdgeInfo { vertexInfo, edge ->
            val result = resultQueue.dequeue()
            assertEquals(result.vertex, vertexInfo)
            assertEquals(result.edge, edge)
        }
    }
}