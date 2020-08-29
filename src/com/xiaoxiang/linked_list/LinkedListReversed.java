package com.xiaoxiang.linked_list;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2020/8/26 6:38
 * @Auther 梁伟
 * @Description 链表逆序,不申请额外空间
 */
public class LinkedListReversed {
    public static void main(String[] args) {
        /*ListNode node1 = new ListNode(1);
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

        //反转整个链表
        ListNode.print(node1);
        System.out.println();
        ListNode reverse = reverse(node1);
        ListNode.print(reverse);

        //区间反转
        ListNode reverseBetween = reverseBetween(node1, 2, 5);
        ListNode.print(reverseBetween);*/


        //用于构建相交链表
/*        ListNode node1B = new ListNode(2);
        ListNode node2B = new ListNode(4);
        ListNode node3B = new ListNode(6);

        node1B.next = node2B;
        node2B.next = node3B;
        node3B.next = node7;

        ListNode intersectionNode = getIntersectionNode(node1, node1B);
        System.out.println(intersectionNode);*/

        //构建成环链表
        /*ListNode node1C = new ListNode(1);
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

        ListNode detectCycle = detectCycle(node1C);
        System.out.println(detectCycle);*/



        //构建用于切分的链表
       /*ListNode node1D = new ListNode(1);
        ListNode node2D = new ListNode(4);
        ListNode node3D = new ListNode(3);
        ListNode node4D = new ListNode(2);
        ListNode node5D= new ListNode(5);
        ListNode node6D = new ListNode(2);

        node1D.next = node2D;
        node2D.next = node3D;
        node3D.next = node4D;
        node4D.next = node5D;
        node5D.next = node6D;

        ListNode partition = partition(node1D, 3);
        ListNode.print(partition);*/



        //用于构建深度克隆链表
        /*ListNode node1E = new ListNode(6);
        ListNode node2E = new ListNode(3);
        ListNode node3E = new ListNode(1);
        ListNode node4E = new ListNode(7);
        ListNode node5E = new ListNode(2);

        node1E.next = node2E;
        node2E.next = node3E;
        node3E.next = node4E;
        node4E.next = node5E;

        node1E.random = node3E;
        node2E.random = node4E;
        node3E.random = node3E;
        node5E.random = node4E;

        ListNode copyRandomList = copyRandomList(node1E);
        ListNode.print(copyRandomList);*/


        //构建链表用于合并升序链表
/*        ListNode node1F = new ListNode(1);
        ListNode node2F = new ListNode(4);
        ListNode node3F = new ListNode(6);

        ListNode node4F = new ListNode(0);
        ListNode node5F = new ListNode(5);
        ListNode node6F = new ListNode(7);

        node1F.next = node2F;
        node2F.next = node3F;
        node4F.next = node5F;
        node5F.next = node6F;

        ListNode mergeTwoLists = mergeTwoLists(node1F, node4F);
        ListNode.print(mergeTwoLists);*/


        //构建用于合并k个升序链表的集合
        ListNode node1G = new ListNode(1);
        ListNode node2G = new ListNode(4);
        ListNode node3G = new ListNode(5);

        node1G.next = node2G;
        node2G.next = node3G;

        ListNode node4G = new ListNode(1);
        ListNode node5G = new ListNode(3);
        ListNode node6G = new ListNode(4);

        node4G.next = node5G;
        node5G.next = node6G;

        ListNode node7G = new ListNode(2);
        ListNode node8G = new ListNode(6);

        node7G.next = node8G;

        ListNode[] lists = {node1G, node4G, node7G};
        ListNode listNode = mergeKLists(lists);
        ListNode.print(listNode);
    }

    /**
     * 采用分治法,先分到2个链表，然后调用两个链表合并函数进行两两合并
     * 将所有升序链表合并到一个升序链表中，返回合并后的链表
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * @param lists 所有要合并链表的头节点，长度是多少就有多少个链表
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        //归并到最后只剩下一个节点的链表，说明完成了合并
        if (lists.length == 1) {
            return lists[0];
        }
        //只剩下两个链表，直接进行合并
        if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }
        //当所有链表被切分到只有1个或2个链表的时候即可完成合并。所以需要切分减小入参调用的规模。分治
        int mid = lists.length / 2;
        ListNode[] sub1Lists = new ListNode[mid];
        ListNode[] sub2Lists = new ListNode[lists.length - mid];

        for (int i = 0; i < mid; i++) {
            sub1Lists[i] = lists[i];
        }

        for (int i = mid; i < lists.length; i++) {
            sub2Lists[i-mid] = lists[i];
        }
        //返回切分后的结果
        ListNode l1 = mergeKLists(sub1Lists);
        ListNode l2 = mergeKLists(sub2Lists);

        //合并
        return mergeTwoLists(l1, l2);
    }


    /**
     * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tempHead = new ListNode(Integer.MAX_VALUE);
        ListNode result = tempHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tempHead.next = l1;
                l1 = l1.next;
            } else {
                tempHead.next = l2;
                l2 = l2.next;
            }
            tempHead = tempHead.next;
        }

        //两个链表长度不等时，将其中一个剩余链表接上
        if (l1 != null) {
            tempHead.next = l1;
        } else if (l2 != null) {
            tempHead.next = l2;
        }
        return result.next;
    }


    /**
     * 通过构建节点编号和随机节点的map，来构建随机指针
     * @param head
     * @return
     */
    public static ListNode copyRandomList(ListNode head) {
        //创建用于记录原链表节点对应的编号，数据结构：当前节点地址---节点编号
        Map<ListNode, Integer> numMap = new HashMap<>();
        //记录新链表，节点编号--地址
        Map<Integer, ListNode> newNumMap = new HashMap<>();
        //新链表头节点，方便构建新链表
        ListNode preNewHead = new ListNode(Integer.MAX_VALUE);
        //备份新链表起始位置，用于返回结果
        ListNode result = preNewHead;
        ListNode listNode;
        int index = 0;

        ListNode curr = head;
        while (curr != null) {
            //构建新链表
            listNode = new ListNode(curr.val);
            preNewHead.next = listNode;
            preNewHead = listNode;
            //记录旧链表的节点地址--编号映射
            numMap.put(curr, index);
            //记录新链表编号--节点地址
            newNumMap.put(index, listNode);
            index++;
            curr = curr.next;
        }

        //两个链表都从头开始跑，构建random指针
        curr = head;
        ListNode currNewList = result.next;
        while (curr != null) {
            //获取原链表节点对应的random节点
            ListNode oldRandomNode = curr.random;
            //从原链表中获取random节点编号
            Integer randomNum = numMap.get(oldRandomNode);
            //经过以上两部我们就可以知道当前节点对应的random节点的编号,再通过newNumMap用编号获取新链表对应的random节点建立关联
            currNewList.random = newNumMap.get(randomNum);

            curr = curr.next;
            currNewList = currNewList.next;
        }
        return result.next;
    }




    /**
     * @auther 梁伟
     * @Description 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     *  输入: head = 1->4->3->2->5->2, x = 3
     *  输出: 1->2->2->4->3->5
     * @Date 2020/8/28 7:04
     * @Param [head, x] 链表头节点，作为切分点的值
     * @return com.xiaoxiang.linked_list.ListNode
     **/
    public static ListNode partition(ListNode head, int x) {
        //创建两个临时头节点，分别用来存放比切分点大的和比切分点小的
        ListNode lessHead = new ListNode(Integer.MAX_VALUE);
        ListNode moreHead = new ListNode(Integer.MAX_VALUE);
        //备份初始lessHead和moreHead用于返回结果
        ListNode result = lessHead;
        ListNode moreHeadBackup = moreHead;

        //将链表中的每一个节点分别挂到lessHead和moreHead所在链表
        while (head != null) {
            ListNode temp = head.next;
            if (head.val >= x) {
                moreHead.next = head;
                moreHead = head;
            } else {
                lessHead.next = head;
                lessHead = head;
            }
            head = temp;
        }
        //将lessHead和moreHead连接
        lessHead.next = moreHeadBackup.next;
        return result.next;
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
    ListNode random;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(ListNode root) {
        ListNode curr = root;
        List<ListNode> list = new ArrayList<>();
        while (curr != null && !list.contains(curr)) {
            System.out.print(curr.val + "\t");
            list.add(curr);
            curr = curr.next;
        }
        System.out.println();
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