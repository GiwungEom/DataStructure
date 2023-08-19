package com.gw.study.datastructure.table

import junit.framework.TestCase.assertEquals
import org.junit.Test

class GwTableOpenAddressingTest {

    private val dataSize = 15
    private val table = GwTableOpenAddressing<Int, Employee>(
        hashFunction1 =  { it % dataSize },
        hashFunction2 = { 1 + it % 7 }
    )

    private infix fun GwTableOpenAddressing<Int, Employee>.generateData(dataSize: Int) {
        repeat(dataSize) {
            this[it] = Employee(empNum = it, name = "name $it", age = 20 + it)
        }
    }

    @Test
    fun table_set_equalsTrue() {
        table generateData dataSize
        assertEquals(dataSize, table.numberOfData)
    }

    @Test
    fun table_delete_equalsTrue() {
        table generateData dataSize
        repeat(dataSize) {
            table.delete(it)
        }

        assertEquals(0, table.numberOfData)
        assertEquals(false, table.delete(0))
    }

    @Test
    fun table_contains_equalsTrue() {
        table generateData dataSize
        assertEquals(true, table.contains(1))
        assertEquals(true, table.contains(3))
        assertEquals(true, table.contains(5))
        assertEquals(false, table.contains(100))
    }

    @Test
    fun table_collision_equalsTrue() {
        table[3] = generateEmployee(3)
        table[18] = generateEmployee(18)
        table[33] = generateEmployee(33)

        assertEquals(3, table[3].empNum)
        assertEquals(18, table[18].empNum)
        assertEquals(33, table[33].empNum)
    }

    private fun generateEmployee(data: Int) = Employee(empNum = data, name = "name $data", age = 20 + data)
}