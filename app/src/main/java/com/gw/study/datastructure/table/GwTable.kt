package com.gw.study.datastructure.table

/**
 *  배열 기반 테이블 (table, map, dictionary)
 *  해쉬 충돌 미적용 상태.
 *  직접 해쉬 함수 등록
 *  테이블 슬롯 및 슬롯 상태 존재 함.
 */
class GwTable<K, V>(
    private val hashFunction: (K) -> Int
) {

    var numberOfData: Int = 0
        private set

    private val table: Array<Slot<K, V>?> = Array(size = 100) { null }

    operator fun get(key: K): V {
        val hash = hashFunction(key)
        return table[hash]?.let {
            if (it.status == SlotStatus.INUSE) {
                it.value
            } else {
                throw IllegalStateException("$key is Deleted")
            }
        } ?: throw IllegalStateException("Not Fount Key : $key")
    }

    operator fun set(key: K, value: V) {
        val hash = hashFunction(key)
        table[hash] = Slot(key = key, value = value, status = SlotStatus.INUSE)
        numberOfData++
    }

    fun contains(key: K): Boolean {
        val hash = hashFunction(key)
        return table[hash]?.status == SlotStatus.INUSE
    }

    fun delete(key: K): Boolean {
        val hash = hashFunction(key)
        return if (table[hash]?.status == SlotStatus.INUSE) {
            table[hash] = table[hash]?.copy(status = SlotStatus.DELETED)
            true.also { numberOfData-- }
        } else {
            false
        }
    }
}