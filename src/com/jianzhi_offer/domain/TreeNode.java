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
}
