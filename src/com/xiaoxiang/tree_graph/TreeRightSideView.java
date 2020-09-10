package com.xiaoxiang.tree_graph;

import java.util.ArrayList;
import java.util.List;

/**
 * author:w_liangwei
 * date:2020/9/10
 * Description: 二叉树右视图 LeetCode199
 */
public class TreeRightSideView {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;

        List<Integer> rightSideView = rightSideView(node1);
        System.out.println(rightSideView);
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    /**
     * 逆序遍历：根结点-->右节点-->左节点。这样保证每层最先访问的是最右侧的节点,将每层最先访问的节点保存即可
     * @param root 当前结点
     * @param depth  节点在哪层
     * @param result 结果集
     */
    private static void dfs(TreeNode root, int depth, List<Integer> result) {
        if (root == null) {
            return;
        }

        //当深度在n层，result放入第n该层元素后，则result中元素个数大于该层深度，即深度从0开始。
        //如放完了第二层，result中有2个元素，而第二层的深度索引时1。只有到第3层时，且result未放入第三层的元素，此时深度索引是2，result中的元素个数也是2。
        //也就是首次出现相等的时候加入元素就是该层最右侧的元素
        if (depth == result.size()) {
            result.add(root.val);
        }
        depth++;
        dfs(root.right, depth, result);
        dfs(root.left, depth, result);
    }
}
