package com.gw.study.datastructure.table

/**
 *  배열 기반 테이블 (table, map, dictionary)
 *  해쉬 충돌 - 열린 어드레싱 이중해쉬 적용
 *  직접 해쉬 함수 등록
 *  테이블 슬롯 및 슬롯 상태 존재 함.
 *  hash 가 배열 사이즈 넘어 갈 시에 따른 처리 부분 미적용.
 */
class GwTableOpenAddressing<K, V>(
    private val hashFunction1: (K) -> Int,
    private val hashFunction2: (K) -> Int
) : GwTable<K, V> {

    var numberOfData: Int = 0
        private set

    private val dataSize: Int = 100
    private val table: Array<Slot<K, V>?> = Array(size = dataSize) { null }

    override operator fun get(key: K): V {
        var hash = hashFunction1(key)
        if (table[hash]?.key != key) {
            do {
                hash += hashFunction2(key)
            } while (table[hash]?.key != key)
        }

        return table[hash]?.let {
            if (it.status == SlotStatus.INUSE) {
                it.value
            } else {
                throw IllegalStateException("$key is Deleted")
            }
        } ?: throw IllegalStateException("Not Fount Key : $key")
    }

    override operator fun set(key: K, value: V) {
        var hash = hashFunction1(key)
        while (table[hash]?.status == SlotStatus.INUSE) {
            hash += hashFunction2(key)
        }
        table[hash] = Slot(key = key, value = value, status = SlotStatus.INUSE)

        numberOfData++
    }

    override fun contains(key: K): Boolean {
        var hash = hashFunction1(key)
        if (table[hash]?.key != key) {
            do {
                hash += hashFunction2(key)
                if (hash > dataSize) return false
            } while (table[hash]?.key == key)
        }

        return table[hash]?.status == SlotStatus.INUSE && table[hash]?.key == key
    }

    override fun delete(key: K): Boolean {
        val hash = hashFunction1(key)
        return if (table[hash]?.status == SlotStatus.INUSE) {
            table[hash] = table[hash]?.copy(status = SlotStatus.DELETED)
            true.also { numberOfData-- }
        } else {
            false
        }
    }
}