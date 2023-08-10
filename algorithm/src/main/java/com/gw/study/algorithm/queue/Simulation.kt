package com.gw.study.algorithm.queue

import com.gw.study.datastructure.queue.GwQueueBasedOnArray
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.util.EnumMap

/**
 *  큐 활용을 위한 햄버거 시뮬레이션 프로그램
 *  1시간 동안 15초에 1명씩 주문
 *  한명의 고객은 하나의 버거만 주문
 *  모든 고객은 무작위 메뉴 선택
 *  햄버거 만드는 사람 1명
 *  위의 경우 어느 정도 크기의 대기실이 필요 한가?
 */
class Simulation {

    private val waitingRoomSize = 100
    private val orderTime = 15 * 1000L
    private val hour = 60 * 1000L * 60
    private enum class Burger(val cookTime: Int) {
        CheeseBurger(12),
        BeefBurger(15),
        DoubleBurger(24)
    }

    suspend fun work() = coroutineScope {
        val queue: GwQueueBasedOnArray<Burger> = GwQueueBasedOnArray(waitingRoomSize)
        val totalOrder: MutableMap<Burger, Int> = EnumMap(Burger::class.java)

        // 주문
        launch {
            while (true) {
                val order = (0..2).random()
                val burger = Burger.values()[order]
                totalOrder[burger] = (totalOrder[burger] ?: 0) + 1
                queue.enqueue(burger)
                delay(orderTime)
            }
        }

        // 제작
        launch {
            var cookTime = 0
            while (true) {
                if (cookTime == 0 && !queue.isEmpty()) {
                    cookTime = queue.dequeue().cookTime
                }
                delay(1000)
                cookTime--
            }
        }

        // 점심 시간
        delay(hour)
        coroutineContext.job.children.forEach {
            it.apply {
                cancel(CancellationException("Launch Time is Over"))
                join()
            }
        }

        // 결과
        Array(totalOrder.size) { "" }.apply {
            totalOrder.keys.forEachIndexed { index, burger ->
                this[index] = "${burger.name} : ${totalOrder[burger]}"
            }
            forEach {
                println(it)
            }.run { println("Waiting room size : $waitingRoomSize") }
        }
    }
}