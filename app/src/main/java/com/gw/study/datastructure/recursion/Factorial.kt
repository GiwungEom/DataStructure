package com.gw.study.datastructure.recursion

/**
 * 팩토리얼
 * 3! = 3 * 2 * 1
 * 1! = 1
 */
class Factorial {

    fun calc(n: Int): Int =
        if (n >= 1) {
            n * calc(n - 1)
        } else {
            1
        }
}