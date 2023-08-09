package com.gw.study.algorithm.stack

import com.gw.study.datastructure.list.GwCircularLinkedList
import com.gw.study.datastructure.stack.GwStackBasedOnLinkedList

/**
 *  스택 활용 계산기 알고리즘
 *  중위 표기법 수식을 후위 표기법 수식으로 변환
 *  그 후 후위 표기법 계산
 */
class Calculator {

    fun calc(infixExpression: String): Int {
        val postfixExpression: String = convertToPostfixExpression(infixExpression = infixExpression)
        return calcPostfix(postfixExpression)
    }

    // 중위 표기법 수식을 후위 표기법으로 변경
    private fun convertToPostfixExpression(infixExpression: String): String {
        // 연산자 순서 값 획득
        fun Char.getOperatorOrder(): Int = when (this) {
                '(' -> 1
                '+','-' -> 3
                '*','/' -> 5
                else -> throw IllegalStateException("Unknown Operator : $this")
        }

        // 연산자 우선 순위 비교
        fun compareOperator(op1: Char, op2: Char): Int = op1.getOperatorOrder() - op2.getOperatorOrder()

        val infixExpressionCharArr = infixExpression.toCharArray()
        // 데이터 순서 보장을 위한 tail 삽입 LinkedList 사용.
        val postfixExpression = GwCircularLinkedList<Char>()
        val operatorStack = GwStackBasedOnLinkedList<Char>()

        infixExpressionCharArr.forEach {
            if (it.isDigit()) {
                postfixExpression.insertBackend(it)
            } else {
                // 닫기 괄호 일경우 '(' 만날때 까지 operatorStack 을 pop 한다.
                if (it == ')') {
                    while (requireNotNull(operatorStack.peek()) != '(') {
                        postfixExpression.insertBackend(requireNotNull(operatorStack.pop()))
                    }
                    // operatorStack 안에 있는 '('를 pop 한다.
                    operatorStack.pop()
                } else {
                    if (operatorStack.empty()) {
                        operatorStack.push(it)
                    } else {
                        while (compareOperator(requireNotNull(operatorStack.peek()), it) >= 0 && it != '(') {
                            postfixExpression.insertBackend(requireNotNull(operatorStack.pop()))
                        }

                        operatorStack.push(it)
                    }
                }
            }
        }

        while (!operatorStack.empty()) {
            postfixExpression.insertBackend(requireNotNull(operatorStack.pop()))
        }

        val charArray = CharArray(size = postfixExpression.numberOfCount)
        repeat(postfixExpression.numberOfCount) { count ->
            charArray[count] = requireNotNull(if (count == 0) postfixExpression.first()?.also { println("first $it") } else postfixExpression.next()?.also { println("next $it") })
        }

        return String(charArray)
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