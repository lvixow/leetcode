package com.xiaoxiang.tree_graph;

import com.xiaoxiang.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * author:w_liangwei
 * date:2020/9/9
 * Description: 二叉树最近公共祖先 LeetCode 236
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);

        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);

        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;

        TreeNode lowestCommonAncestor = lowestCommonAncestor(node1, node8, node4);
        System.out.println(lowestCommonAncestor);
    }

    /**
     * 返回最近公共祖先。先获取根结点到每个子节点的路径，然后比较路径来查找最近公共祖先
     * @param root 根节点
     * @param p 子节点1
     * @param q 子节点2
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        //获取根结点到子节点路径
        List<TreeNode> p_path = new ArrayList<>();
        List<TreeNode> q_path = new ArrayList<>();
        dfs(root, q, new Stack<>(), q_path, false);
        dfs(root, p, new Stack<>(), p_path, false);

        //双指针来寻找最近公共祖先，当最快的指针发现此时两条路径不是同一个节点时，则前一个节点就是最近的公共祖先
        int index = 1;
        int preIndex = 0;
        while (!p_path.isEmpty() && !q_path.isEmpty() && p_path.get(index) == q_path.get(index)) {
           index++;
           preIndex++;
        }
        return q_path.get(preIndex);
    }

    /**
     * 查找根节点到子节点间的路径
     * @param node 根结点
     * @param searchNode 子节点
     * @param path  路径栈
     * @param result 最终结果
     * @param finish 路径是否查找完成的标记
     */
    private static void dfs(TreeNode node, TreeNode searchNode, Stack<TreeNode> path, List<TreeNode> result, boolean finish) {
        if (node == null || finish) {
            return;
        }
        //当前节点加入路径栈
        path.push(node);
        //找到了添加路径到结果集，并将查找标记设置为已完成，从而不用继续往下查找了
        if (searchNode.val == node.val) {
            result.addAll(path);
            finish = true;
        }

        //继续查找左右节点
        dfs(node.left, searchNode, path, result, finish);
        dfs(node.right, searchNode, path, result, finish);

        //节点出栈返回到上层节点
        path.pop();
    }
}
