package com.jianzhi_offer;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * author:w_liangwei
 * date:2020/12/16
 * Description:
 * 1. 头插法生成倒序链表然后遍历
 * 2. 先放入栈取出时就是倒序
 */
public class ReversePrint {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;


        //java推荐的一种栈的实现方式
        Deque<Integer> stack = new ArrayDeque<>();
        while (node1 != null) {
            stack.push(node1.val);
            node1 = node1.next;
        }
        int[] res = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            res[index++] = stack.pop();
        }
        System.out.println(Arrays.toString(res));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
}

