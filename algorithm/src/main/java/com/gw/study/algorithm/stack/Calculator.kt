package com.gw.study.algorithm.stack

import com.gw.study.algorithm.notation.ReversePolishNotation
import com.gw.study.algorithm.notation.ReversePolishNotationImpl
import com.gw.study.datastructure.stack.GwStackBasedOnLinkedList

/**
 *  스택 활용 계산기 알고리즘
 *  중위 표기법 수식을 후위 표기법 수식으로 변환
 *  그 후 후위 표기법 계산
 */
class Calculator(
    private val reversePolishNotation: ReversePolishNotation = ReversePolishNotationImpl()
) {

    fun calc(infixExpression: String): Int {
        // 중위 표기법 -> 후위 표기법 변환
        val postfixExpression: String = reversePolishNotation.convertToPostfixExpression(infixExpression = infixExpression)
        return calcPostfix(postfixExpression)
    }

    // 후위 표기법 계산 결과 반환
    private fun calcPostfix(postfixExpression: String): Int {

        fun calculate(operator: String, numberFirst: Int, numberSecond: Int): Int = when (operator) {
            "+" -> numberFirst + numberSecond
            "-" -> numberFirst - numberSecond
            "*" -> numberFirst * numberSecond
            "/" -> numberFirst / numberSecond
            else -> throw IllegalStateException("Unknown Operator : $operator")
        }

        val postfixExpressionCharArr = postfixExpression.toCharArray()
        // 숫자 및 계산 결과를 담을 스택 변수
        val numberStack = GwStackBasedOnLinkedList<String>()

        postfixExpressionCharArr.forEach {
            if (it.isDigit()) {
                numberStack.push(it.toString())
            } else {
                // 스택에서 먼저 나온 숫자가 식의 두번째 숫자가 된다.
                val numberSecondPos = requireNotNull(numberStack.pop())
                val numberFirstPos = requireNotNull(numberStack.pop())

                numberStack.push(
                    calculate(
                        operator = it.toString(),
                        numberFirst = numberFirstPos.toInt(),
                        numberSecond = numberSecondPos.toInt()
                    ).toString()
                )
            }
        }

        return requireNotNull(numberStack.pop()).toInt()
    }

}