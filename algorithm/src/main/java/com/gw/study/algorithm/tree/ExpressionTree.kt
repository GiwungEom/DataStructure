package com.gw.study.algorithm.tree

import com.gw.study.algorithm.notation.ReversePolishNotation
import com.gw.study.algorithm.notation.ReversePolishNotationImpl
import com.gw.study.datastructure.stack.GwStack
import com.gw.study.datastructure.stack.GwStackBasedOnArray
import com.gw.study.datastructure.tree.GwBinaryTree


/**
 *  수식 트리 알고리즘
 *  중위 표기법 수식을 후위 표기법 수식으로 변환
 *  후위 표기법 수식을 이진 트리로 변환
 *  이진트리 계산
 */
class ExpressionTree(
    // 후위 표기법 변환
    private val reversePolishNotation: ReversePolishNotation = ReversePolishNotationImpl()
) {

    fun calc(infixExpression: String): Int {
        val postfixExpression = reversePolishNotation.convertToPostfixExpression(infixExpression)
        val bTree: GwBinaryTree<Char> = makeExpressionTree(postfixExpression)
        return evaluateExpressionTree(bTree)
    }

    fun makeExpressionTree(postfixExpression: String): GwBinaryTree<Char> {
        val stack: GwStack<GwBinaryTree<Char>> = GwStackBasedOnArray(stackSize = postfixExpression.length)
        postfixExpression.forEach {
            if (it.isDigit()) {
                stack.push(GwBinaryTree(it))
            } else {
                stack.push(
                    GwBinaryTree(it).apply {
                        rightTree = stack.pop()
                        leftTree = stack.pop()
                    }
                )
            }
        }
        return requireNotNull(stack.pop())
    }

    private fun evaluateExpressionTree(bTree: GwBinaryTree<Char>): Int {

        if (bTree.leftTree == null && bTree.rightTree == null) return bTree.data.digitToInt()

        val op1 = evaluateExpressionTree(requireNotNull(bTree.leftTree))
        val op2 = evaluateExpressionTree(requireNotNull(bTree.rightTree))

        return when (bTree.data) {
            '+' -> op1 + op2
            '-' -> op1 - op2
            '*' -> op1 * op2
            '/' -> op1 / op2
            else -> throw IllegalStateException("Unknown Operator ${bTree.data}")
        }
    }
}