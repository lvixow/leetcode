package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

/**
 * author:w_liangwei
 * date:2021/1/15
 * Description: offer 28
 */
public class IsSymmetric {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.setLiftRight(node2, node3);
        node2.setLiftRight(node4, node5);
        node3.setLiftRight(node6, node7);

        System.out.println(node1);

        boolean symmetric = isSymmetric(node1);
        System.out.println(symmetric);
    }

    public static boolean isSymmetric(TreeNode root) {
        //只有一个根结点还是null的时候，这时候是镜像的
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    public static boolean dfs(TreeNode node1, TreeNode node2) {
        //两个节点都为null则就是镜像的，但不能往下继续走了，因为没有节点可比较了
        if (node1 == null && node2 == null) return true;
        //两个节点不为null且值相同，则继续比较node1的左子树和node2的右子树是否相同、
        //node1的右子树和node2的左子树是否相同，并非比较当前节点的左右节点相同，自己可以看下图就明白了
        if (node1!= null && node2 != null && node1.val == node2.val) {
            //比较node1的左子树和node2的右子树是否相同
            //比较node1的右子树和node2的左子树是否相同
            return dfs(node1.left, node2.right) && dfs(node1.right, node2.left);
        } else {
            //其中一个节点为null的情况，另一个不为null，说明不是镜像
            return false;
        }
    }
}
