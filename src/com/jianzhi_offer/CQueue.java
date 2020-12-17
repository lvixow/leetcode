package com.jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * author:w_liangwei
 * date:2020/12/17
 * Description:
 */
public class CQueue {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        obj.appendTail(1);
        obj.appendTail(2);
        obj.appendTail(3);
        obj.appendTail(4);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
        obj.appendTail(5);
        obj.appendTail(6);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
    }

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public CQueue() {
        this.stack1 = new ArrayDeque<>();
        this.stack2 = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        //如果不为空优先弹出上一次已经排好序的可出队元素
        if (!stack2.isEmpty()) return stack2.pop();
        if (stack1.isEmpty()) return -1;
        //排好序的队列元素为空时，将栈中元素转过来
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
