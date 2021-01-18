package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author:w_liangwei
 * date:2021/1/18
 * Description: offer32 -2
 *
 * 与上一题类似，只是将同一层级的元素一次性弹出放到一起
 */
public class LevelOrder2 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);

        treeNode1.setLiftRight(treeNode2, treeNode3);
        treeNode3.setLiftRight(treeNode4, treeNode5);


        List<List<Integer>> levelOrder = levelOrder(treeNode1);
        System.out.println(levelOrder);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        List<TreeNode> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            //存放同一层的被弹出元素
            List<Integer> sameLayerElementList = new ArrayList<>();
            //弹空同一层级的元素并加入下一层级元素
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                sameLayerElementList.add(poll.val);
                if (poll.left != null) temp.add(poll.left);
                if (poll.right != null) temp.add(poll.right);
            }
            queue.addAll(temp);
            temp.clear();
            res.add(sameLayerElementList);
        }
        return res;
    }
}
