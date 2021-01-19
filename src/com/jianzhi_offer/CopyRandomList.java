package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/19
 * Description: offer 35
 *
 * 1. 每个节点后复制一个相同的节点进行连接，形成1-1-2-2-3-3这样的序列
 * 2. 构建复制链表的随机指针
 * 3. 分离原链表与复制链表
 */
public class CopyRandomList {
    public static void main(String[] args) {
//        Node node1 = new Node(7);
//        Node node2 = new Node(13);
//        Node node3 = new Node(0);
//        Node node4 = new Node(11);
//        Node node5 = new Node(4);
//        Node node6 = new Node(10);
//        Node node7 = new Node(2);
//        Node node8 = new Node(1);

//        node1.next = node2;
//        node2.next = node4;
//        node2.random = node1;
//        node4.next = node6;
//        node4.random = node8;
//        node6.next = node8;
//        node6.random = node4;


        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node2.random = node1;

        Node copyRandomList = copyRandomList(node1);
        System.out.println(copyRandomList);
    }

    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        Node curr = head;
        //在每个节点后复制一个当前节点，即形成1-1-2-2-3-3-null这样的链
        while (curr != null) {
            Node node = new Node(curr.val);
            Node temp = curr.next;
            curr.next = node;
            node.next = temp;
            curr = temp;
        }

        //对复制后的节点关联随机指针
        curr = head;
        while (curr != null) {
            //当前节点后就是一个自己的复制节点
            Node currNodeCopyNode = curr.next;
            //如果当前节点random指针关联了节点则也为复制节点关联
            if (curr.random != null) {
                //curr.random是当前节点的random，这个节点的后边就是当前节点的复制节点要关联的random
                currNodeCopyNode.random = curr.random.next;
            }
            //移动到自己的复制节点后，相当于一次走2步，跳过复制节点，处理一下节点的random
            curr = currNodeCopyNode.next;
        }

        curr = head;
        //保存复制链表的头节点，防止分离链表后丢失
        Node res = head.next;
        //对复制链表和原链表进行分离
        while (curr != null) {
            Node currNodeCopyNode = curr.next;
            //恢复原链表的next指向
            curr.next = currNodeCopyNode.next;
            //分离复制链表的next指向
            if (currNodeCopyNode.next != null) {
                currNodeCopyNode.next = currNodeCopyNode.next.next;
            }
            //指向已经恢复了，所以下一个节点就是原链表的节点，相当于原节点和复制节点两个节点同时处理，处理过后跳过这两个节点继续取两个再处理
            curr = curr.next;
        }
        return res;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
