package com.gw.study.datastructure.table

import junit.framework.TestCase
import org.junit.Test

class GwTableClosedAddressingTest {

    private val dataSize = 15
    private val table = GwTableClosedAddressing<Int, Employee>(
        hashFunction =  { it % dataSize }
    )

    private infix fun GwTable<Int, Employee>.generateData(dataSize: Int) {
        repeat(dataSize) {
            this[it] = Employee(empNum = it, name = "name $it", age = 20 + it)
        }
    }

    @Test
    fun table_set_equalsTrue() {
        table generateData dataSize
        TestCase.assertEquals(dataSize, table.numberOfData)
    }

    @Test
    fun table_delete_equalsTrue() {
        table generateData dataSize
        repeat(dataSize) {
            table.delete(it)
        }

        TestCase.assertEquals(0, table.numberOfData)
        TestCase.assertEquals(false, table.delete(0))
    }

    @Test
    fun table_contains_equalsTrue() {
        table generateData dataSize
        TestCase.assertEquals(true, table.contains(1))
        TestCase.assertEquals(true, table.contains(3))
        TestCase.assertEquals(true, table.contains(5))
        TestCase.assertEquals(false, table.contains(100))
    }

    @Test
    fun table_collision_equalsTrue() {
        table[3] = generateEmployee(3)
        table[18] = generateEmployee(18)
        table[33] = generateEmployee(33)

        TestCase.assertEquals(3, table[3].empNum)
        TestCase.assertEquals(18, table[18].empNum)
        TestCase.assertEquals(33, table[33].empNum)
    }

    private fun generateEmployee(data: Int) = Employee(empNum = data, name = "name $data", age = 20 + data)
}