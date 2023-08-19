package com.gw.study.datastructure.table

import com.gw.study.datastructure.list.GwLinkedList

/**
 *  배열 및 연결리스트 기반 테이블
 *  해쉬 충돌 - 닫힌 어드레싱 체이닝 적용
 *  hash 가 배열 사이즈 넘어 갈 시에 따른 처리 부분 미적용.
 */
class GwTableClosedAddressing<K, V>(
    private val hashFunction: (K) -> Int
) : GwTable<K, V> {

    private data class Slot<K, V>(val key: K, val value: V)

    var numberOfData: Int = 0
        private set

    private val dataSize: Int = 100
    private val table: Array<GwLinkedList<Slot<K, V>>> = Array(size = dataSize) { GwLinkedList() }

    override fun get(key: K): V {
        val hash = hashFunction(key)
        var data = table[hash].first()

        if (data?.key == key) {
            return requireNotNull(data?.value)
        } else {
            while (table[hash].next()?.also { data = it } != null) {
                if (data?.key == key) {
                    return requireNotNull(data?.value)
                }
            }
        }
        throw Exception("Unknown Key :$key")
    }

    override fun set(key: K, value: V) {
        if (contains(key)) {
            throw Exception("Exists Key : $key")
        }

        val hash = hashFunction(key)
        table[hash].insert(Slot(key = key, value = value))
        numberOfData++
    }

    override fun contains(key: K): Boolean {
        val hash = hashFunction(key)
        var data = table[hash].first()

        if (data?.key == key) {
            return true
        } else {
            while (table[hash].next()?.also { data = it } != null) {
                if (data?.key == key) {
                    return true
                }
            }
        }
        return false
    }

    override fun delete(key: K): Boolean {
        val hash = hashFunction(key)
        var data = table[hash].first()

        if (data?.key == key) {
            table[hash].remove()
            return true.also { numberOfData-- }
        } else {
            while (table[hash].next()?.also { data = it } != null) {
                if (data?.key == key) {
                    table[hash].remove()
                    return true.also { numberOfData-- }
                }
            }
        }
        return false
    }
}