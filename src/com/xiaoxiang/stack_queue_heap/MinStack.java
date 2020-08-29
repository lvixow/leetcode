package com.xiaoxiang.stack_queue_heap;

import java.util.Stack;

/**
 * @Date 2020/8/29 18:00
 * @Auther 梁伟
 * @Description
 * 数据栈和最小值
 */
public class MinStack {

    //存储数据
    private Stack<Integer> dataStack = new Stack<>();

    //存储最小值
    private Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    /**
     * @auther 梁伟
     * @Description 压入一个数据，更新当前最小值栈与该数据对应的最小值
     * @Date 2020/8/29 18:10
     * @Param [x]
     * @return void
     **/
    public void push(int x) {
        dataStack.push(x);
        //更新最小值栈对应的最小值
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            Integer min = minStack.peek();
            if (x < min) {
                minStack.push(x);
            } else {
                minStack.push(min);
            }
        }
    }

    public void pop() {
        //最小值栈和数据栈同时弹出
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-2);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("===============================");

        minStack.push(0);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("===============================");

        minStack.push(-5);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println("===============================");

        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
