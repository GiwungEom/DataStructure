package com.gw.study.datastructure.queue

interface GwQueue<T> {

    val numberOfData: Int
    fun enqueue(data: T)
    fun dequeue(): T
    fun peek(): T

}