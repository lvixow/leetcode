package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/13
 * Description: offer 22
 * 双指针，一个先走K步，然后两个指针同时走，直到后一个指针为null，此时返回前一个指针
 */
public class GetKthFromEnd {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = getKthFromEnd(node1, 2);
        System.out.println(head);
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        ListNode q = head;
        //先叫q走k步
        for (int i = 0; i < k; i++) {
            q = q.next;
        }
        //然后同时走，q是null的时候，p就是倒数第k个
        while (q != null) {
            q = q.next;
            p = p.next;
        }
        return p;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
}
