package com.gw.study.datastructure.recursion

import org.junit.Test

class HanoiTowerTest {
    @Test
    fun hanoiTower_move_print() {
        HanoiTower().move(3, 'A', 'B', 'C')
    }
}