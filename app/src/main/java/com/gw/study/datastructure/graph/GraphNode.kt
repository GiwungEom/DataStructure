package com.gw.study.datastructure.graph

data class GraphNode(
    val vertex: Vertex,
    val edge: Edge
)

enum class Vertex {
    A,B,C,D,E,F,G
}

data class Edge(
    val fromVertex: Vertex,
    val toVertex: Vertex,
    val weight: Int
)