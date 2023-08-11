package com.gw.study.algorithm.notation

import com.gw.study.datastructure.list.GwCircularLinkedList
import com.gw.study.datastructure.stack.GwStackBasedOnLinkedList

// 후위 표기법 제공
interface ReversePolishNotation {
    // 중위 표기법 수식을 후위 표기법으로 변경
    fun convertToPostfixExpression(infixExpression: String): String
}

class ReversePolishNotationImpl : ReversePolishNotation {

    // 중위 표기법 수식을 후위 표기법으로 변경
    override fun convertToPostfixExpression(infixExpression: String): String {
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
}