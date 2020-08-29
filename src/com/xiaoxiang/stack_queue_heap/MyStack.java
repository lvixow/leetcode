package com.xiaoxiang.stack_queue_heap;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Date 2020/8/29 17:03
 * @Auther 梁伟
 * @Description 使用队列模拟栈
 */
public class MyStack {

    //存放数据的队列
    private ConcurrentLinkedQueue<Integer> dateQueue = new ConcurrentLinkedQueue<>();

    //临时队列用于添加元素时修正元素顺序，使其满足栈的要求
    private ConcurrentLinkedQueue<Integer> tempQueue = new ConcurrentLinkedQueue<>();

    /** Initialize your data structure here. */
    public MyStack() {}


    /**
     * @auther 梁伟
     * @Description 添加数据时，先放入到tempQueue。然后将dateQueue中所有数据添加到tempQueue。这时顺序已经满足了栈的操作要求，
     * 所以再次将tempQueue中元素全部加入dateQueue。即完成了元素的添加
     * @Date 2020/8/29 17:21
     * @Param [x]
     * @return void
     **/
    public void push(int x) {
        tempQueue.add(x);
        tempQueue.addAll(dateQueue);
        dateQueue.clear();
        dateQueue.addAll(tempQueue);
        tempQueue.clear();
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return dateQueue.poll();
    }

    /** Get the top element. */
    public int top() {
        return dateQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return dateQueue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        System.out.println(myStack.empty());
        System.out.println(myStack.top());
        System.out.println("=======================================");

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println("=======================================");

        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }
}
