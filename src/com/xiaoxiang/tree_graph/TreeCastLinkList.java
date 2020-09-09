package com.xiaoxiang.tree_graph;

/**
 * @Date 2020/9/10 6:20
 * @Auther 梁伟
 * @Description 二叉树转链表 LeetCode 114
 */
public class TreeCastLinkList {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);

        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        flatten(node1);
        System.out.println(node1);
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        //分别处理左右节点
        flatten(root.left);
        flatten(root.right);

        //备份右边，防止拼接左节点时右节点丢失
        TreeNode temp = root.right;
        //拼接左边到链表
        root.right = root.left;
        //左边置空
        root.left = null;
        //找到链表末尾位置，拼接右边到链表
        while (root.right != null) {
            root = root.right;
        }
        root.right = temp;
    }
}

