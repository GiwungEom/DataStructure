package com.gw.study.datastructure.list

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ArrayListTest {

    private lateinit var list: GwList<Int>
    private val listSize = 10

    @Before
    fun initialize() {
        list = GwArrayList(listSize)
        repeat(listSize) {
            list.insert(it)
        }
    }

    @Test
    fun arraylist_count_equalsTrue() {
        assertEquals(listSize, list.numberOfData)
    }

    @Test
    fun arraylist_first_equalsTrue() {
        assertEquals(0, list.first())
    }

    @Test
    fun arraylist_next_equalsTrue() {
        with (list) {
            first()
            assertEquals(1, next())
        }
    }

    @Test
    fun arraylist_remove_equalsTrue() {
        with(list) {
            first() // 0
            next()  // 1
            next()  // 2
            assertEquals(2, remove())
        }
    }

    @Test
    fun arraylist_reordering_equalsTrue() {
        with(list) {
            first() // 0
            next()  // 1
            next()  // 2
            remove()
            assertEquals(3, next())
        }
    }

    @Test
    fun arraylist_arrayOrder_equalsTrue() {
        with(list) {
            first() // 0
            next()  // 1
            next()  // 2
            remove() // 2
            next() // 3
            remove() // 3

            // expect - 0,1,4,5,6,7,8,9
            var index = 0
            repeat(listSize) {
                if (!(it == 2 || it == 3)) {
                    assertEquals(it, list.getData(index++))
                }
            }
        }
    }
}