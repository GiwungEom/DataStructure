package com.gw.study.datastructure.recursion

/**
 * 피보나치 수열
 * fib(n)   = 0  ==> n=1
 *          = 1  ==> n=2
 *          = n  ==> fib(n-1) + fib(n-2)
 */
class FibonacciSequence {

    fun calc(n: Int): Int = when(n) {
        1 -> 0
        2 -> 1
        else -> calc(n - 1) + calc(n - 2)
    }
}