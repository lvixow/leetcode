package com.xiaoxiang.stack_queue_heap;

import java.util.Stack;

/**
 * @Date 2020/8/29 17:48
 * @Auther 梁伟
 * @Description 使用栈实现队列
 */
public class MyQueue {

    private Stack<Integer> dataStack = new Stack<>();

    private Stack<Integer> tempStack = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /**
     * @auther 梁伟
     * @Description 与队列模拟栈相似，本次采用2个栈模拟队列。添加的方式和模拟栈的时候相同
     * @Date 2020/8/29 17:53
     * @Param [x]
     * @return void
     **/
    public void push(int x) {
        tempStack.push(x);
        tempStack.addAll(dataStack);
        dataStack.clear();
        dataStack.addAll(tempStack);
        tempStack.clear();
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return dataStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return dataStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return dataStack.isEmpty();
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
