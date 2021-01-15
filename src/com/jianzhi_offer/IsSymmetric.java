package com.jianzhi_offer;

import com.jianzhi_offer.domain.TreeNode;

/**
 * author:w_liangwei
 * date:2021/1/15
 * Description: offer 28
 *
 * 做递归思考三步：
 *     递归的函数要干什么？
 *          函数的作用是判断传入的两个树是否镜像。
 *          输入：TreeNode left, TreeNode right
 *          输出：是：true，不是：false
 *     递归停止的条件是什么？
 *          左节点和右节点都为空 -> 倒底了都长得一样 ->true
 *          左节点为空的时候右节点不为空，或反之 -> 长得不一样-> false
 *          左右节点值不相等 -> 长得不一样 -> false
 *     从某层到下一层的关系是什么？
 *          要想两棵树镜像，那么一棵树左边的左边要和二棵树右边的右边镜像，一棵树左边的右边要和二棵树右边的左边镜像
 *          调用递归函数传入左左和右右
 *          调用递归函数传入左右和右左
 *          只有左左和右右镜像且左右和右左镜像的时候，我们才能说这两棵树是镜像的
 *
 *     调用递归函数，我们想知道它的左右孩子是否镜像，传入的值是root的左孩子和右孩子。这之前记得判个root==null。
 *
 */
public class IsSymmetric {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.setLiftRight(node2, node3);
        node2.setLiftRight(node4, node5);
        node3.setLiftRight(node6, node7);

        System.out.println(node1);

        boolean symmetric = isSymmetric(node1);
        System.out.println(symmetric);
    }

    public static boolean isSymmetric(TreeNode root) {
        //只有一个根结点还是null的时候，这时候是镜像的
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    public static boolean dfs(TreeNode node1, TreeNode node2) {
        //两个节点都为null则就是镜像的，但不能往下继续走了，因为没有节点可比较了
        if (node1 == null && node2 == null) return true;
        //两个节点不为null且值相同，则继续比较node1的左子树和node2的右子树是否相同、
        //node1的右子树和node2的左子树是否相同，并非比较当前节点的左右节点相同，自己可以看下图就明白了
        if (node1!= null && node2 != null && node1.val == node2.val) {
            //比较node1的左子树和node2的右子树是否相同
            //比较node1的右子树和node2的左子树是否相同
            return dfs(node1.left, node2.right) && dfs(node1.right, node2.left);
        } else {
            //其中一个节点为null的情况，另一个不为null，说明不是镜像
            return false;
        }
    }
}
