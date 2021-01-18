package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author:w_liangwei
 * date:2021/1/18
 * Description: offer 32 -1
 *
 *
 * 就是一个使用广度优先搜索的遍历，使用一个队列就可以实现
 */
public class LevelOrder {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);

        treeNode1.setLiftRight(treeNode2, treeNode3);
        treeNode3.setLiftRight(treeNode4, treeNode5);

        System.out.println(treeNode1);

        int[] levelOrder = levelOrder(treeNode1);
        System.out.println(Arrays.toString(levelOrder));

    }


    public static int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
