package com.xiaoxiang.stack_queue_heap;

import java.util.Stack;

/**
 * @Date 2020/8/29 17:48
 * @Auther 梁伟
 * @Description 使用栈实现队列
 */
public class MyQueue {

    private Stack<Integer> inputStack = new Stack<>();

    private Stack<Integer> outputStack = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/8/29 17:53
     * @Param [x]
     * @return void
     **/
    public void push(int x) {
        inputStack.push(x);
    }

    /**
     * 在获取元素时，使用临时栈调整到合适位置进行弹出
     * @return
     */
    public int pop() {
        adjust(inputStack, outputStack);
        return outputStack.pop();
    }

    public int peek() {
        adjust(inputStack, outputStack);
        return outputStack.peek();
    }

    private void adjust(Stack<Integer> inputStack, Stack<Integer> outputStack) {
        if (!outputStack.isEmpty()) {
            return;
        }
        //当输出的栈为空，则需要将输入栈中的内容放入输出栈完成对顺序的调整
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);

        System.out.println(myQueue.empty());
        System.out.println(myQueue.peek());
        System.out.println("=========================");

        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println("=======================================");

        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());

    }
}
