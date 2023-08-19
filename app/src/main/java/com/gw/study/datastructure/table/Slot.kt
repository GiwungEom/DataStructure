package com.gw.study.datastructure.table

data class Slot<K, V>(
    val key: K,
    val value: V,
    val status: SlotStatus
)

enum class SlotStatus {
    EMPTY,      // 비어있는 상태
    DELETED,    // 삭제 된 상태
    INUSE       // 저장 된 상태
}