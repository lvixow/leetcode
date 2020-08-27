package com.xiaoxiang.linked_list;


import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/26 6:38
 * @Auther 梁伟
 * @Description 链表逆序,不申请额外空间
 */
public class LinkedListReversed {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        //用于构建相交链表
        ListNode node1B = new ListNode(2);
        ListNode node2B = new ListNode(4);
        ListNode node3B = new ListNode(6);

        node1B.next = node2B;
        node2B.next = node3B;
        node3B.next = node7;

        //构建成环链表
        ListNode node1C = new ListNode(1);
        ListNode node2C = new ListNode(2);
        ListNode node3C = new ListNode(3);
        ListNode node4C = new ListNode(4);
        ListNode node5C= new ListNode(5);
        ListNode node6C = new ListNode(6);
        ListNode node7C = new ListNode(7);

        node1C.next = node2C;
        node2C.next = node3C;
        node3C.next = node4C;
        node4C.next = node5C;
        node5C.next = node6C;
        node6C.next = node7C;
        node7C.next = node3C;


        //反转整个链表
//        ListNode.print(node1);
//        System.out.println();
//        ListNode reverse = reverse(node1);
//        ListNode.print(reverse);

//        System.out.println();
//        System.out.println("==============================================================");
//        ListNode reverseBetween = reverseBetween(node1, 2, 5);
//        ListNode.print(reverseBetween);

//        ListNode intersectionNode = getIntersectionNode(node1, node1B);
//        System.out.println(intersectionNode);

        ListNode detectCycle = detectCycle(node1C);
        System.out.println(detectCycle);
    }


    /**
     * 使用快慢指针跑，当两个指针相遇即有环。根据数学推理，从相遇节点和起始节点同时跑，相同的节点即为成环节点
     * @auther 梁伟
     * @Description 如果链表有环返回成环的节点，否则返回null
     * @Date 2020/8/28 5:55
     * @Param [head]
     * @return ListNode
     **/
    public static ListNode detectCycle(ListNode head) {
        //创建快慢指针
        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;

        //寻找相遇节点
        do {
            //快慢指针都走一步
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                //快指针再走一步
                fast = fast.next;
            }
            //如果节点没有环，则可以被跑完。如果节点有环，则slow和fast会在同一节点相遇
        } while (slow != null && fast != null && slow != fast);

        //因为fast节点最先跑完一个完整的链表，所以它是最快发现链表是否成环的
        if (fast == null) {
            return null;
        }
        meet = fast;
        //根据数学推理，从meet节点和head节点同时跑，相交的节点即为成环节点
        while (meet != head) {
            meet = meet.next;
            head = head.next;
        }
        return meet;
    }



    /**
     * @auther 梁伟
     * @Description 求两个链表的相交节点
     * @Date 2020/8/28 5:22
     * @Param [headA, headB] 两个链表的头节点
     * @return com.xiaoxiang.linked_list.ListNode
     **/
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = headA.getLength();
        int lengthB = headB.getLength();

        //比较两个链表的长度，并将较长的链表移动到和较短链表相同节点个数的起始位置
        if (lengthA > lengthB) {
            headA = forwardLongList(headA, lengthA - lengthB);
        } else {
            headB = forwardLongList(headB, lengthB - lengthA);
        }

        //两个长度相同的链表同时跑，节点的地址值相同时即为同一个节点
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }


    /**
     * @auther 梁伟
     * @Description 返回移动了指定个数节点的链表
     * @Date 2020/8/28 5:38
     * @Param [start, num] 起始节点，移动的节点个数
     * @return com.xiaoxiang.linked_list.ListNode
     **/
    public static ListNode forwardLongList(ListNode start, int num) {
        while (start != null && num > 0) {
            num--;
            start = start.next;
        }
        return start;
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/8/27 6:04
     * @Param [node, start, end] 链表的头节点，要逆序节点在整个链表中的起始索引位置，结束位置
     * @return com.xiaoxiang.linked_list.ListNode
     **/
    public static ListNode reverseBetween(ListNode node, int start, int end) {
        //没有节点需要逆序，直接返回
        if (node == null || start < 0 || end < 0 || end <= start) return null;
        //计算要逆序多少个节点
        int changeLen = end - start + 1;
        //创建临时变量记录start节点的前一个节点，用于逆序后构建新链表
        ListNode preHead = null;

        //start起始为0，表示从头节点开始逆序，不需要寻找preHead。start减为0时是head的位置，preHead即为start减为1的位置
        if (start != 0) {
            //找到preHead和head的位置
            ListNode curr = node;
            while (curr != null && start > 1) {
                start--;
                curr = curr.next;
            }
            preHead = curr;
        }

        //经过上边的步骤反转起始节点是头节点的preHead会是null。接下来从head节点开始反转,head为要反转链表的头节点
        ListNode head = preHead != null ? preHead.next : node;
        //新链表头节点
        ListNode newHead = null;
        //反转部分的头节点变成反转部分的尾结点，记录该节点，用于最后和原链表连接
        ListNode modifyListTail = head;
        while (head != null && changeLen != 0) {
            //与原来逆序整个链表的步骤一致
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
            changeLen--;
        }
        //将反转后后半部分与原来的链表接上
        modifyListTail.next = head;
        //只有逆序的起始节点不是头节点时才需要连接前半部分
        if (preHead != null) {
            //将反转后前半部分与原来的链表接上
            preHead.next = newHead;
            return node;
        } else {
            //当是从头结点开始逆序的话，原先传入的头结点已经被逆序到最后，如果还返回原来的头结点就会导致只能看到原来的头结点和原来链表的剩余部分，前半部分节点从而丢失
            return newHead;
        }
    }


    /**
     * @auther 梁伟
     * @Description 逆序整个链表
     * @Date 2020/8/27 6:02
     * @Param [node]
     * @return com.xiaoxiang.linked_list.ListNode
     **/
    public static ListNode reverse(ListNode node) {
        if (node == null) return null;

        ListNode newHead = null;

        ListNode curr = node;
        while (curr != null) {
            //备份当前节点的下一个节点，防止切断后丢失
            ListNode temp = curr.next;
            //构建新链，并斩断旧链
            curr.next = newHead;
            //将新节点设置为新链表的头结点
            newHead = curr;
            //遍历旧链表的下一节点
            curr = temp;
        }
        return newHead;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static void print(ListNode root) {
        ListNode curr = root;
        List<ListNode> list = new ArrayList<>();
        while (curr != null && !list.contains(curr)) {
            System.out.print(curr.val + "\t");
            list.add(curr);
            curr = curr.next;
        }
    }

    public int getLength() {
        int count = 0;
        ListNode curr = this;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}