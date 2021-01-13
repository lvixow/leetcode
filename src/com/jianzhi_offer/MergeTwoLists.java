package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/13
 * Description: offer 25
 *
 * 方法1：双指针遍历两个链表比较，然后加入新链表
 * 方法2：采用递归的思想节省了一个链表空间，有点类似于分治法的感觉
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        MergeTwoLists.ListNode node1 = new MergeTwoLists.ListNode(1);
        MergeTwoLists.ListNode node2 = new MergeTwoLists.ListNode(2);
        MergeTwoLists.ListNode node3 = new MergeTwoLists.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        MergeTwoLists.ListNode node4 = new MergeTwoLists.ListNode(1);
        MergeTwoLists.ListNode node5 = new MergeTwoLists.ListNode(3);
        MergeTwoLists.ListNode node6 = new MergeTwoLists.ListNode(4);
        node4.next = node5;
        node5.next = node6;
        System.out.println(node1);
        System.out.println(node4);
        ListNode mergeTwoLists = mergeTwoLists(node1, node4);
        System.out.println(mergeTwoLists);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //说明其中一个链表到头了
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        //链表1的当前节点应该排前边，则移动链表1的节点，继续递归子问题
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            //链表2排前边
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
}
