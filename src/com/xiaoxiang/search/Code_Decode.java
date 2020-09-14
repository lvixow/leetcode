package com.xiaoxiang.search;


import com.xiaoxiang.domain.TreeNode;

/**
 * @Date 2020/9/14 6:16
 * @Auther 梁伟
 * @Description leetcode 449
 *
 *
 * 一般的二叉树需要先序和中序两者结合才能还原树结构，而二叉搜索树由于有根结点大于左节点，根结点小于右节点这样的性质
 * 导致可以使用一种遍历就可以确定树结构。如先序8,3,1,6,10,15,可以根据根左右这样的顺序确定8肯定是根结点，而由于二叉
 * 搜索树的特性，碰到的第一个大于8的节点，也就是10是其右节点。而在8-10之间的是其左子树，10之后是右子树
 */
public class Code_Decode {
    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(8);
//        TreeNode node2 = new TreeNode(3);
//        TreeNode node3 = new TreeNode(10);
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(6);
//        TreeNode node6 = new TreeNode(15);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.right = node6;

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        node1.left = node2;

        String serialize = serialize(node1);
        System.out.println(serialize);


        TreeNode deserialize = deserialize(serialize);
        System.out.println(deserialize);
    }

    /**
     * @auther 梁伟
     * @Description 使用前序遍历进行编码，  根---左节点---右节点
     * @Date 2020/9/14 6:45
     * @Param [root]
     * @return java.lang.String
     **/
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.substring(0, sb.length() -1);
    }

    private static void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }


    /**
     * @auther 梁伟
     * @Description 根据编码还原二叉搜索树
     * @Date 2020/9/14 6:45
     * @Param [data]
     * @return com.xiaoxiang.domain.TreeNode
     **/
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] split = data.split(",");
        return buildTreeNode(split, 0, split.length -1);
    }

    private static TreeNode buildTreeNode(String[] split, int left, int right) {
        if (left > right) {
            return null;
        }
        //找到第一个比根结点大的元素，则该元素就是根结点的右节点.这是一句前序遍历和二叉搜索树的性质得出的
        TreeNode root = new TreeNode(Integer.parseInt(split[left]));
        //如果没有右节点，则index默认为right+1，这样做的原因是为了在执行右节点的递归构建时。触发left > right的条件
        //从而退出右节点的构建。即满足了我们没有右节点时不构建右节点
        int index = right + 1;
        //因为right传递的是数组最后一个位置的索引，所以此处是有等号的
        for (int i = left + 1; i <= right; i++) {
            if (Integer.parseInt(split[i]) > root.val) {
                index = i;
                break;
            }
        }
        //左节点所在区间，left是根结点，left+1到index-1是左节点
        root.left = buildTreeNode(split, left + 1, index -1);
        //右节点所在区间是index到right。如果没有右节点则index是right+1，这样总能保证下一次递归会被退出，从而不去构建右节点
        root.right = buildTreeNode(split, index, right);
        return root;
    }
}
