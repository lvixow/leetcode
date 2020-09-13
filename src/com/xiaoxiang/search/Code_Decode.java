package com.xiaoxiang.search;


import com.xiaoxiang.domain.TreeNode;

/**
 * @Date 2020/9/14 6:16
 * @Auther 梁伟
 * @Description leetcode 449
 */
public class Code_Decode {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(15);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        String serialize = serialize(node1);
        System.out.println(serialize);
    }

    /**
     * @auther 梁伟
     * @Description 使用前序遍历进行编码
     * @Date 2020/9/14 6:45
     * @Param [root]
     * @return java.lang.String
     **/
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.substring(0, sb.length() -1);
    }

    private static void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }


    /**
     * @auther 梁伟
     * @Description 根据编码还原二叉搜索树
     * @Date 2020/9/14 6:45
     * @Param [data]
     * @return com.xiaoxiang.domain.TreeNode
     **/
    public static TreeNode deserialize(String data) {



        return null;
    }
}
