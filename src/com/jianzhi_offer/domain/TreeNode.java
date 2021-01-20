package com.jianzhi_offer.domain;

/**
 * author:w_liangwei
 * date:2021/1/15
 * Description:
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        val = x;
    }
    public void setLiftRight(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

    public void setLiftRight(int left, int right) {
        this.left = new TreeNode(left);
        this.right = new TreeNode(right);
    }

    public TreeNode(int val, int left, int right) {
        this.val = val;
        this.left = new TreeNode(left);
        this.right = new TreeNode(right);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
