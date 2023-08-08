package com.gw.study.datastructure.stack

import junit.framework.TestCase.assertEquals
import org.junit.Test

class StackTest {

    companion object {
        private const val STACK_SIZE = 5
    }

    private val stackOnArray: GwStackBasedOnArray<Int> by lazy { GwStackBasedOnArray(STACK_SIZE) }
    private val stackOnLinkedList: GwStackBasedOnLinkedList<Int> by lazy { GwStackBasedOnLinkedList() }
    private val stackOnCircularLinkedList: GwStackBasedOnCircularLinkedList<Int> by lazy { GwStackBasedOnCircularLinkedList() }

    // Stack 데이터 생성 중위 표기
    private infix fun GwStack<Int>.generateData(stackSize: Int) = this.apply { repeat(stackSize) { push(it) } }

    @Test
    fun stackBasedOnArray_push_equalsTrue() {
        with(stackOnArray) {
            this generateData STACK_SIZE
            assertEquals(STACK_SIZE, numberOfData)
        }
    }

    @Test
    fun stackBasedOnArray_pop_equalsTrue() {
        with(stackOnArray) {
            this generateData STACK_SIZE
            repeat(STACK_SIZE) {
                assertEquals((STACK_SIZE - 1) - it, pop())
            }
        }
    }

    @Test
    fun stackBasedOnArray_peek_equalsTrue() {
        with(stackOnArray) {
            this generateData STACK_SIZE
            repeat(STACK_SIZE) {
                assertEquals((STACK_SIZE - 1) - it, peek())
                pop()
            }
        }
    }

    @Test
    fun stackBasedOnLinkedList_push_equals() {
        with(stackOnLinkedList) {
            this generateData STACK_SIZE
            assertEquals(STACK_SIZE, numberOfData)
        }
    }

    @Test
    fun stackBasedOnLinkedList_pop_equals() {
        with(stackOnLinkedList) {
            this generateData STACK_SIZE
            repeat(STACK_SIZE) {
                assertEquals((STACK_SIZE - 1) - it, pop())
            }
        }
    }

    @Test
    fun stackBasedOnLinkedList_peek_equals() {
        with(stackOnLinkedList) {
            this generateData STACK_SIZE
            repeat(STACK_SIZE) {
                assertEquals((STACK_SIZE - 1) - it, peek())
                pop()
            }
        }
    }

    @Test
    fun stackOnCircularLinkedList_push_equals() {
        with(stackOnCircularLinkedList) {
            this generateData STACK_SIZE
            assertEquals(STACK_SIZE, numberOfData)
        }
    }

    @Test
    fun stackOnCircularLinkedList_pop_equals() {
        with(stackOnCircularLinkedList) {
            this generateData STACK_SIZE
            repeat(STACK_SIZE) {
                assertEquals((STACK_SIZE - 1) - it, pop())
            }
        }
    }

    @Test
    fun stackOnCircularLinkedList_peek_equals() {
        with(stackOnCircularLinkedList) {
            this generateData STACK_SIZE
            repeat(STACK_SIZE) {
                assertEquals((STACK_SIZE - 1) - it, peek())
                pop()
            }
        }
    }
}