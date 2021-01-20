package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author:w_liangwei
 * date:2021/1/20
 * Description: offer 36
 *
 * BST树中序遍历正好是有序的序列，将序列放到队列中，然后遍历队列完成循环链表构建
 */
public class TreeToDoublyList {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4, 2, 5);
        node1.left.setLiftRight(1,3);
//        System.out.println(node1);
        TreeNode treeNode = treeToDoublyList(node1);
        System.out.println(treeNode);
    }

    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList();
        dfs(root, queue);
        //弹出第一个节点作为头节点
        TreeNode head = queue.poll();
        TreeNode pre = head;
        TreeNode curr = head;
        //将全部有序节点进行前后连接
        while (!queue.isEmpty()) {
            curr = queue.poll();
            pre.right = curr;
            curr.left = pre;
            pre = curr;
        }
        //连接头尾节点形成循环链表，退出上一个循环时curr核pre指向的是同一个节点
        head.left = curr;
        curr.right = head;
        return head;
    }

    public static void dfs(TreeNode node, Queue queue) {
        if (node == null) return;
        dfs(node.left, queue);
        queue.offer(node);
        dfs(node.right, queue);
    }
}
