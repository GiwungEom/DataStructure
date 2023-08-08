package com.gw.study.datastructure.stack

interface GwStack<T> {

    val numberOfData: Int
    fun empty(): Boolean
    fun pop(): T?
    fun push(data: T)
    fun peek(): T?

}