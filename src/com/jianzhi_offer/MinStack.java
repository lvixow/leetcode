package com.jianzhi_offer;

import java.util.Stack;

/**
 * author:w_liangwei
 * date:2021/1/15
 * Description: offer 30
 *
 * 使用最小值栈来将获取最小值的复杂度降到O(1)，保证最小值随着元素的添加和移除永远在最小值栈栈顶
 */
public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            //当前加入的值比最小值栈栈顶的值小，则压入到最小值栈
            int min = x < minStack.peek() ? x : minStack.peek();
            minStack.push(min);
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
