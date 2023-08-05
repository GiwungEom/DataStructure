package com.gw.study.datastructure.recursion

/**
 *  하노이 타워 (n 원반 수, A B C 기둥)
 *  1. n-1 개의 원반을 A 기둥에서 B 기둥으로 이동
 *  2. 맨아래의 원반을 A 기둥에서 C 기둥으로 이동
 *  3. n-1 개의 원반을 B 기둥에서 C 기둥으로 이동
 */
class HanoiTower {

    fun move(n: Int, from: Char, by: Char, to: Char) {
        if (n == 1) {
            println("원반 1을 $from 에서 ${to}로 이동")
        } else {
            move(n-1, from, to, by)
            println("원반 ${n}을 $from 에서 ${to}로 이동")
            move(n-1, by, from, to)
        }
    }

}