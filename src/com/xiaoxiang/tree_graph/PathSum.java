package com.xiaoxiang.tree_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * author:w_liangwei
 * date:2020/9/9
 * Description: 路径总和 LeetCode 113
 *
 * 示例：
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *                5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 *
 * 返回: [[5, 4, 11, 2],[5, 8, 4, 5]]
 */
public class PathSum {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);

        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);

        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.left = node9;
        node6.right = node10;

        List<List<Integer>> result = pathSum(node1, 22);
        System.out.println(result);
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<Integer> path = new Stack<>();
        dfs(root, path, sum, 0, result);
        return result;
    }

    /**
     *  深度遍历记录叶子节点路径
     * @param node  当前被遍历节点
     * @param path  前边已经遍历经过的路径
     * @param sum   路径所要求的和
     * @param pathValue 当前路径经过的和，用于和sum比较
     * @param result    最终符合要求的路径结果
     */
    private static void dfs(TreeNode node, Stack<Integer> path, int sum, int pathValue, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        //加入当前节点的值,并更新当前路径总和
        pathValue = pathValue + node.val;
        path.push(node.val);
        //当前节点是叶子节点,并且路径值和指定值相等。则将路径添加到结果集
        if (node.left == null && node.right == null && pathValue == sum) {
            result.add(new ArrayList<>(path));
        }
        //分别继续往下遍历处理左右节点
        dfs(node.left, path, sum, pathValue, result);
        dfs(node.right, path, sum, pathValue, result);

        //如果到达最底部节点仍不满足要求，则依次退栈。继续向其它分支遍历
        pathValue = pathValue - node.val;
        path.pop();
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }