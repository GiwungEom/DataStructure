package com.gw.study.datastructure.list

import org.jetbrains.annotations.TestOnly

interface GwList<T> {

    val numberOfData: Int
    fun insert(data: T): Boolean
    fun first(): T?
    fun next(): T?
    fun remove(): T?
    @TestOnly
    fun getData(i: Int): T?
}