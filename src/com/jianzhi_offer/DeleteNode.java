package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/4
 * Description: offer 18
 */
public class DeleteNode {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode head = deleteNode(node1, 5);
        System.out.println(head);
    }

    public static ListNode deleteNode(ListNode head, int val) {
        ListNode curr = head;
        ListNode pre = null;
        //只要当前节点不为null且数值对不上就一直往下找
        while (curr != null && curr.val != val) {
            pre = curr;
            curr = curr.next;
        }
        //跳出循环时要不为null，要不就是链表中所有节点没有该值
        if (curr != null) {
            //删除的是头结点，则这时pre是null，未记录任何节点，所以直接返回头结点的下一个节点
            if (curr == head) {
                ListNode temp = curr.next;
                curr.next = null;
                return temp;
            }
            //删的不是头结点，直接将pre的next连接到当前待删除节点的next
            pre.next = curr.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
}
