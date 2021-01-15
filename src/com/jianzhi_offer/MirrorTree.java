package com.jianzhi_offer;


import com.jianzhi_offer.domain.TreeNode;

/**
 * author:w_liangwei
 * date:2021/1/15
 * Description: offer 27
 *
 * 进行dfs遍历，在遍历的过程中交换当前节点的左右子树
 */
public class MirrorTree {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(7);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(3);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(9);

        treeNode1.setLiftRight(treeNode2, treeNode3);
        treeNode2.setLiftRight(treeNode4, treeNode5);
        treeNode3.setLiftRight(treeNode6, treeNode7);

        System.out.println(treeNode1);
        mirrorTree(treeNode1);
        System.out.println(treeNode1);
    }

    //在遍历的基础上进行改造
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        //保存左节点防止交换时覆盖
        TreeNode temp = root.left;
        //当前节点左子树更换为当前节点的右子树
        root.left = mirrorTree(root.right);
        //当前节点的右子树更换为当前节点的左子树
        root.right = mirrorTree(temp);
        return root;
    }
}
