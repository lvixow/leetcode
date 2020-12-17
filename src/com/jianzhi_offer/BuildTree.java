package com.jianzhi_offer;

import java.util.HashMap;

/**
 * author:w_liangwei
 * date:2020/12/17
 * Description: offer 07
 * 先序可以确定根，在中序找到根就可以确定当前根的左右子树，然后再到先序确定左右子树的根，一直到构建完成
 */
public class BuildTree {

    static HashMap<Integer, Integer> map = new HashMap<>();
    int[] preorder;

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        BuildTree buildTree = new BuildTree();
        TreeNode root = buildTree.buildTree(preorder, inorder);
        System.out.println(root);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = dfs(0, 0, inorder.length - 1);
        return root;
    }

    /**
     * 由pre_root_idx确定当前树的根，由in_left_idx和in_right_idx来圈出要构建子树的范围
     * 即我们知道了树的中序序列和根结点
     * @param pre_root_idx  根结点在先序遍历中的位置
     * @param in_left_idx   被构建树在中序遍历中的起始位置
     * @param in_right_idx  被构建树在中序遍历中的结束位置
     * @return
     */
    public TreeNode dfs(int pre_root_idx, int in_left_idx, int in_right_idx) {
        if (in_left_idx > in_right_idx) {
            return null;
        }
        //生成根
        int rootVal = preorder[pre_root_idx];
        TreeNode root = new TreeNode(rootVal);
        //划分左右子树并递归生成
        //通过根节点的值获取到当前根结点在中序序列中的位置，然后划分左右子树
        int root_inorder_idx = map.get(rootVal);
        TreeNode left = dfs(pre_root_idx + 1, in_left_idx, root_inorder_idx -1);
        //由上一步确定的左子树在中序序列中的区间，可以得知左子树有多少节点，而先序序列中 根 + 左子树 + 右子树 = 整个子树
        //我们现在知道了根节点在先序序列中的索引，左子树有多少节点，那么就可以推出右子树在先序序列中的起始节点，也就是右子树的根结点
        TreeNode right = dfs(pre_root_idx + (root_inorder_idx - in_left_idx) + 1, root_inorder_idx + 1, in_right_idx);

        //将左右子树挂到根结点
        root.left = left;
        root.right = right;
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
