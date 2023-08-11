package com.gw.study.algorithm.tree

import com.gw.study.algorithm.notation.ReversePolishNotation
import com.gw.study.algorithm.notation.ReversePolishNotationImpl
import com.gw.study.algorithm.stack.Calculator
import com.gw.study.datastructure.queue.GwQueueBasedOnLinkedList
import com.gw.study.datastructure.tree.GwBinaryTreeTraversal
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExpressionTreeTest {

    @Test
    fun expressionTree_makeExpressionTree_equalsTrue() {

        // Traversal Result List
        val result = listOf(
            // Preorder
            GwQueueBasedOnLinkedList<Char>().apply {
                enqueue('*')
                enqueue('+')
                enqueue('1')
                enqueue('2')
                enqueue('7')
            },
            // Postorder
            GwQueueBasedOnLinkedList<Char>().apply {
                enqueue('1')
                enqueue('2')
                enqueue('+')
                enqueue('7')
                enqueue('*')
            },
            // Inorder
            GwQueueBasedOnLinkedList<Char>().apply {
                enqueue('1')
                enqueue('+')
                enqueue('2')
                enqueue('*')
                enqueue('7')
            }
        )

        // 중위 수식
        val infixExpression = "(1+2)*7"
        // 후위 표기법 변환
        val reversePolishNotation: ReversePolishNotation = ReversePolishNotationImpl()
        val postFixExpression = reversePolishNotation.convertToPostfixExpression(infixExpression)

        // 후휘 표기법 -> 수식 트리 변환
        val binaryTree = ExpressionTree().makeExpressionTree(postFixExpression)

        // 트리 순회
        val bTreeTraversal = GwBinaryTreeTraversal<Char>()

        bTreeTraversal.preorderTraversal(binaryTree) { data: Char ->
            assertEquals(
                result[0/*preorder*/].dequeue(),
                data
            )
        }
        bTreeTraversal.postorderTraversal(binaryTree) { data: Char ->
            assertEquals(
                result[1/*postorder*/].dequeue(),
                data
            )
        }
        bTreeTraversal.inorderTraversal(binaryTree) { data: Char ->
            assertEquals(
                result[2/*inorder*/].dequeue(),
                data
            )
        }
    }

    @Test
    fun expressionTree_calc_equalsTrue() {
        val infixExpression = "(1+2)*7"         // 중위 수식
        val calculator = Calculator()           // 스택 기반 계산
        val expressionTree = ExpressionTree()   // 수식 트리 기반 계산

        assertEquals(
            calculator.calc(infixExpression),
            expressionTree.calc(infixExpression)
        )
    }
}