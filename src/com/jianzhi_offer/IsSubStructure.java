package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/14
 * Description: offer 26
 *
 * A    大的那个树，其中包含子树
 * B    小的那个树，子树
 *
 * 执行流程
 *  1. 比较根结点和子树的起始节点是否匹配
 *      匹配      进入A左节点和B左节点比较，A右节点和B右节点比较
 *      不匹配     比较根结点的左节点和右节点看是否与子树的起始节点匹配
 *  2.当在A中找到了子树的起始节点，那么进入A左节点和B左节点比较，A右节点和B右节点比较模式，直到比较完成
 */
public class IsSubStructure {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(2);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;





        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(1);
        treeNode6.left = treeNode7;
        System.out.println(treeNode1);
        System.out.println(treeNode6);
        System.out.println(isSubStructure(treeNode1, treeNode6));
    }

    public static boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) return false;
        //优先匹配当前节点，当当前节点未匹配成功说明它不是一个子结构的起始节点，
        //需要分别使用当前节点的左节点和右节点分别再进行匹配
        //这个3个或相当于寻找子结构在大的树中的起始位置，当找到起始位置后就进入了，左节点左节点比较，右节点右节点比较的模式
        return dfs(a,b) || isSubStructure(a.left, b) || isSubStructure(a.right, b);
    }

    public static boolean dfs(TreeNode a, TreeNode b) {
        //b是被匹配的子树，当b上的节点全部匹配完的时候就是null，所以此时整个被匹配子树中的节点全部被匹配成功
        if (b == null) return true;
        //a是用来找子树的树，当a为null说明整个树已经搜索完了还没有搜索到对应的子树
        //或a，b节点不等也不是符合要求的子树
        if (a == null || a.val != b.val) return false;
        //当前节点匹配成功后，那么还需要保证当前节点的左节点和子结构的左节点匹配，当前节点的右节点和子结构的右节点匹配
        return dfs(a.left, b.left) && dfs(a.right, b.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
