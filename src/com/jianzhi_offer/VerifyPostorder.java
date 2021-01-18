package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/18
 * Description: offer 33
 *
 * 根据后序遍历所以最后一个节点肯定是根节点，又根据二叉搜索树的特性即左节点小于根结点，右节点大于根结点，
 * 然后将树分为左子树和右子树，依次在子树中继续这样做，如果所有子树都满足则整个树就是二叉搜索树
 *
 *
 * 此题的最优方法是单调栈
 *
 */
public class VerifyPostorder {
    public static void main(String[] args) {
        int[] postOrder = {1,3,2,6,5};
        boolean verifyPostorder = verifyPostorder(postOrder);
        System.out.println(verifyPostorder);
    }

    public static boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) return true;
        return dfs(postorder, 0 , postorder.length -1);
    }

    /**
     * 左右指针主要用来确定数组的范围
     * @param postOrder 后序遍历数组
     * @param left  子树在数组中的左指针
     * @param right 子树在数组中的右指针
     * @return
     */
    public static boolean dfs(int[] postOrder, int left, int right) {
        //当指针相遇说明所有子树都符合二叉搜索树的要求
        if (left>= right) return true;
        //根据后序遍历的特性，最后一个节点肯定是根节点
        int root = postOrder[right];
        int k = left;
        //判断是否左子树都比根节点小
        while (postOrder[k] < root) k++;
        //记录左子树和右子树的分隔点
        int mid = k;
        //判断右子树是否都比根节点大，当k在向后移动的过程中，逐渐向root靠近，因为root在最右端，
        //当k到达root的位置时，此时postOrder[k] == root，所以必然会跳出循环，无需额外条件限制
        while (postOrder[k] > root) k++;
        //如果k和右端点相等，说明指针顺利走到了数的最后一个节点，即此时k和right都指向根节点
        //当前树检查符合要求后，继续来检查它的左子树和右子树
        //只要当前树符合要求，它的左右子树符合要求，那么才是整个树符合要求
        //k属于右子树，最后一个节点right是当前树的根节点，所以不能被包含在右子树中
        return k == right && dfs(postOrder, left, mid -1) && dfs(postOrder, mid, right - 1);
    }
}
