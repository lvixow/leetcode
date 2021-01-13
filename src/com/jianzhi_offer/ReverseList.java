package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/13
 * Description: offer 24
 * 最初想到的是直接创建头结点使用头插法，但是空间耗费较多。所以使用双指针指向紧挨个节点，叫他们之间互换后
 * 然后一起移动在进行下一次互换，直到前边的那个指针为null，即走到了链表的末尾
 */
public class ReverseList {
    public static void main(String[] args) {
        ReverseList.ListNode node1 = new ReverseList.ListNode(1);
        ReverseList.ListNode node2 = new ReverseList.ListNode(2);
        ReverseList.ListNode node3 = new ReverseList.ListNode(3);
        ReverseList.ListNode node4 = new ReverseList.ListNode(4);
        ReverseList.ListNode node5 = new ReverseList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode reverseList = reverseList(node1);
        System.out.println(reverseList);
    }

    public static ListNode reverseList(ListNode head) {
        //链表为空或只有一个节点
        if (head == null || head.next == null) return head;
        //前后挨着的双指,不要使pre对准head，curr对准head.next，那么做反转后的链表最后一个节点也就是头结点它
        //的next我们从来就没有改变过，一直还是指向正序的下一个节点。而它的下一个节点在倒序后指向了它，出现了
        //倒序后原来处于头部的两个节点在循环指向。
        //为了防止这种情况出现，我们提前就改变正序第一个节点的next叫它指向null，所以pre的初始值为null
        //出现情况: 原链表12345，倒序后543212121212.......
//        ListNode pre = head;
//        ListNode curr = head.next;
        ListNode pre = null;
        ListNode curr = head;
        ListNode temp;
        //只要不为null说明没走到链表
        while (curr != null) {
            //保存下一个节点防止赋值后被覆盖拿不到
            temp = curr.next;
            //当前节点指向它的前一个节点完成倒序
            curr.next = pre;
            //两个指针往前走一下
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
}
