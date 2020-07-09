package com.top;

import java.util.Stack;

/**
 * author:w_liangwei
 * date:2020/7/9
 * Description:
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        //输出：7 -> 0 -> 8
        //原因：342 + 465 = 807
        ListNode l1_node1 = new ListNode(0);
//        ListNode l1_node2 = new ListNode(4);
//        ListNode l1_node3 = new ListNode(3);
//        l1_node1.next = l1_node2;
//        l1_node2.next = l1_node3;

        ListNode l2_node1 = new ListNode(7);
        ListNode l2_node2 = new ListNode(3);
//        ListNode l2_node3 = new ListNode(4);
        l2_node1.next = l2_node2;
//        l2_node2.next = l2_node3;

        addTwoNumbers2(l1_node1, l2_node1).print();
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //使用数组记录结果，留一位防止高位相加后进位溢出
        int maxSize = l1.size();
        if (l2.size() > l1.size()) {
            maxSize = l2.size();
        }
        int[] result = new int[maxSize + 1];

        //创建两个栈方便逆序相加
        Stack<ListNode> l1_stack = new Stack<>();
        Stack<ListNode> l2_stack = new Stack<>();
        l1_stack.push(l1);
        l2_stack.push(l2);
        while (l1.next != null) {
            l1_stack.push(l1.next);
            l1 = l1.next;
        }
        while (l2.next != null) {
            l2_stack.push(l2.next);
            l2 = l2.next;
        }


        int index = result.length -1;
        boolean carryBitFlag = false;
        while (!l1_stack.isEmpty() && !l2_stack.isEmpty() && l1_stack.peek() != null && l2_stack.peek() != null) {
            int sum = l1_stack.pop().val + l2_stack.pop().val;
            //查看是否有上一次加完需要进位的
            if (carryBitFlag) {
                result[index] = result[index] + 1;
                carryBitFlag = false;
            }
            if (sum < 10) {
                //当前值加上上一次进位的值
                result[index] = sum + result[index];
            } else {
                carryBitFlag = true;
                result[index] = sum - 10 + result[index];
            }
            index--;
        }

        //记录相等处的位置
        int tempIndex = index;

        //处理两个链表长度不相等时，其中一个还有元素剩余的情况
        if (!l1_stack.isEmpty()) {
            while (l1_stack.peek() != null) {
                result[index] = l1_stack.pop().val;
            }
        }

        if (!l2_stack.isEmpty()) {
            while (l2_stack.peek() != null) {
                result[index] = l2_stack.pop().val;
            }
        }

        //处理相等部分相加完成后的最后一次进位
        if (carryBitFlag) {
            result[tempIndex] = result[tempIndex] + 1;
        }

        //生成最终结果链表
        ListNode resultListNode = new ListNode(result[result.length -1]);
        ListNode temp = resultListNode;
        for (int i = result.length - 2; i >= 1; i--) {
            ListNode node = new ListNode(result[i]);
            temp.next = node;
            temp = node;
        }

        //处理最高位进位
        if (carryBitFlag) {
            temp.next = new ListNode(1);
        }
        return resultListNode;
    }


    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //创建两个指针分别指向两个链表用于相加
        ListNode q = l1;
        ListNode p = l2;
        //创建进位标识
        int carry = 0;
        //创建头结点，方便返回
        ListNode head = new ListNode(0);
        //记录当前节点，方便组成新的链表
        ListNode current = head;
        while (q != null || p != null) {
            //对下一个待处理节点为空的处理
            int q_val = q == null ? 0 : q.val;
            int p_val = p == null ? 0 : p.val;
            //本应该从各位开始加从后往前进位，但是由于链表是逆序所以相应的要往后进位就相当于实际计算时的往前进位
            int sum = carry + q_val + p_val;
            //相加后的值如果超过10则进位
            carry = sum / 10;
            //当前位置的值
            sum = sum % 10;
            //组成结果链表
            current.next = new ListNode(sum);
            current = current.next;
            //移动相加的指针,对当前节点为空的处理
            if (q != null) q = q.next;
            if (p != null) p = p.next;
        }
        //完成相加后的最后一位的进位
        if (carry > 0) {
            current.next = new ListNode(1);
        }
        return head.next;
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public int size() {
        ListNode startNode = this;
        int size = 1;
        while (startNode.next != null) {
            startNode = startNode.next;
            size++;
        }
        return size;
    }

    public void print() {
        ListNode temp = this;
        while (temp != null) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
        }
    }
}