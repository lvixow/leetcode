package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * author:w_liangwei
 * date:2021/1/19
 * Description: offer 34
 */
public class PathSum {
    public static void main(String[] args) {
//        TreeNode treeNode1 = new TreeNode(5);
//        TreeNode treeNode2 = new TreeNode(4);
//        TreeNode treeNode3 = new TreeNode(8);
//        TreeNode treeNode4 = new TreeNode(11);
//        TreeNode treeNode5 = new TreeNode(13);
//        TreeNode treeNode6 = new TreeNode(4);
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode8 = new TreeNode(2);
//        TreeNode treeNode9 = new TreeNode(5);
//        TreeNode treeNode10 = new TreeNode(1);
//
//        treeNode1.setLiftRight(treeNode2, treeNode3);
//        treeNode2.left = treeNode4;
//        treeNode3.setLiftRight(treeNode5, treeNode6);
//        treeNode4.setLiftRight(treeNode7, treeNode8);
//        treeNode6.setLiftRight(treeNode9, treeNode10);

//        System.out.println(treeNode1);
//        List<List<Integer>> lists = pathSum(treeNode1, 22);

        TreeNode treeNode1 = new TreeNode(-2);
        TreeNode treeNode2 = new TreeNode(-3);
        treeNode1.right = treeNode2;
        List<List<Integer>> lists = pathSum(treeNode1, -5);
        System.out.println(lists);

    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, res, new ArrayList<>(), sum);
        return res;
    }


    /**
     *
     * @param node 当前路径中要加入的节点
     * @param sum  当前和是多少了
     * @param res  最终结果
     * @param temp 当前路径上的节点
     * @param targetSum 设立的目标和
     */
    public static void dfs(TreeNode node, int sum, List<List<Integer>> res, List<Integer> temp, int targetSum) {
        //将节点加入路径并更新当前路径和值
        temp.add(node.val);
        sum = sum + node.val;
        //如果已经到达叶子节点并且路径上的节点的和符合目标要求，则加入到结果集
        if (node.left == null && node.right == null && sum == targetSum) {
            res.add(new ArrayList<>(temp));
            return;
        }

        if (node.left != null) {
            //因为当节点条件不满足时不会放到temp中，也不会被求和，所以不需要在dfs后再移除上一次递归添加的结果，因为就没有添加过
            dfs(node.left, sum, res, temp, targetSum);
            //由于基本类型变量int的特性，导致其实只是复制了本地变量到递归的下一个栈，而不是使用的地址，所以无需对sum进行扣减
            temp.remove(temp.size() -1);
        }
        if (node.right != null) {
            dfs(node.right, sum, res, temp, targetSum);
            temp.remove(temp.size() -1);
        }
    }
}