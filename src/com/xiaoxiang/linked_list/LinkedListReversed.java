package com.xiaoxiang.linked_list;

/**
 * @Date 2020/8/26 6:38
 * @Auther 梁伟
 * @Description 链表逆序
 */
public class LinkedListReversed {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        Node.print(node1);
        System.out.println();
        Node reverse = reverse(node1);
        Node.print(reverse);
    }


    public static Node reverse(Node node) {
        if (node == null) return null;

        Node newHead = null;

        Node curr = node;
        while (curr != null) {
            //备份当前节点的下一个节点，防止切断后丢失
            Node temp = curr.getNext();
            //构建新链，并斩断旧链
            curr.setNext(newHead);
            //将新节点设置为新链表的头结点
            newHead = curr;
            //遍历旧链表的下一节点
            curr = temp;
        }
        return newHead;
    }
}





class Node {
    private Integer val;

    private Node next;

    public Node() {}

    public Node(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public Node getNext() {
        return next;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static void print(Node root) {
        Node curr = root;
        while (curr != null) {
            System.out.print(curr.val + "\t");
            curr = curr.next;
        }
    }
}