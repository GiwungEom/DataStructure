package com.gw.study.datastructure.table

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GwTableTest {

    private val table = GwSimpleTable<Int, Employee> { it % 100 }
    private val dataSize = 50

    @Before
    fun initialize() {
        repeat(dataSize) {
            table[it] = Employee(empNum = it, name = "name $it", age = 20 + it)
        }
    }

    @Test
    fun table_set_equalsTrue() {
        assertEquals(dataSize, table.numberOfData)
    }

    @Test
    fun table_delete_equalsTrue() {
        repeat(dataSize) {
            table.delete(it)
        }

        assertEquals(0, table.numberOfData)
        assertEquals(false, table.delete(0))
    }
}