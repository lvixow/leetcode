package com.xiaoxiang.domain;

/**
 * @Date 2020/9/14 6:24
 * @Auther 梁伟
 * @Description
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    //当前节点左子树节点数量
    public int count;

    public TreeNode() {}
    public TreeNode(int x) { val = x; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}