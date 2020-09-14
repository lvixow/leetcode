package com.xiaoxiang.recursion_recall;

import com.xiaoxiang.domain.TreeNode;
import java.util.Arrays;
import java.util.List;

/**
 * author:w_liangwei
 * date:2020/9/8
 * Description: LeetCode 315
 *
 *
 * 1.归并排序，在合并的过程中可以统计逆序数
 * 2.逆序将数组插入到二叉排序树中，每个节点维护一个左子树的节点数量。逆序插入是为了在插入时直接可以获得到当前数的逆序数
 *
 */
public class InverseNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1};
        List<Integer> countSmaller = countSmaller(nums);
        System.out.println(countSmaller);
    }



    public static List<Integer> countSmaller(int[] nums) {
        //初始化一个integer数组，方便使用和最终结果转换
        Integer[] result = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = 0;
        }
        //起始根结点为null
        TreeNode root = null;
        //逆序遍历nums依次加入到二叉搜索树，这样一旦出现后加入节点大于先前加入的节点时，此时就可以直接统计该元素对应的逆序数
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, new TreeNode(nums[i]), result, i);
        }
        return Arrays.asList(result);




        /*List<Integer> result = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return result;
        }

        int[] temp = new int[len];
        int[] res = new int[len];

        // 索引数组，作用：归并回去的时候，方便知道是哪个下标的元素
        int[] indexes = new int[len];
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        mergeAndCountSmaller(nums, 0, len - 1, indexes, temp, res);

        // 把 int[] 转换成为 List<Integer>，没有业务逻辑
        for (int i = 0; i < len; i++) {
            result.add(res[i]);
        }
        return result;*/
    }

    /**
     *
     * @param root  父节点
     * @param node  子节点
     * @param result  保存逆序数结果，使用下标获取该索引位置的逆序数个数
     * @param index 该元素在数组中的索引
     * @return  树的根结点
     */
    private static TreeNode insert(TreeNode root, TreeNode node, Integer[] result, int index) {
        //根结点为null，当前子节点升级为根结点。因为不存在父子关系所以也不用继续执行插入
        if (root == null) {
            root = node;
            return root;
        }
        //小于父节点则插入左子树，否则插入右子树
        if (node.val <= root.val) {
            root.count = root.count + 1;
            root.left = insert(root.left, node, result, index);
        } else {
            //比插入节点node小的节点总数 = 上一次的结果resut[index] + 根结点的count + 根结点本身
            result[index] = result[index] + root.count + 1;
            root.right = insert(root.right, node, result, index);
        }
        return root;
    }










    /**
     * 针对数组 nums 指定的区间 [left, right] 进行归并排序，在排序的过程中完成统计任务
     *
     * @param nums
     * @param left
     * @param right
     */
    private static void mergeAndCountSmaller(int[] nums, int left, int right, int[] indexes, int[] temp, int[] res) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeAndCountSmaller(nums, left, mid, indexes, temp, res);
        mergeAndCountSmaller(nums, mid + 1, right, indexes, temp, res);

        // 归并排序的优化，如果索引数组有序，则不存在逆序关系，没有必要合并
        if (nums[indexes[mid]] <= nums[indexes[mid + 1]]) {
            return;
        }
        mergeOfTwoSortedArrAndCountSmaller(nums, left, mid, right, indexes, temp, res);
    }

    /**
     * [left, mid] 是排好序的，[mid + 1, right] 是排好序的
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param indexes
     * @param temp
     * @param res
     */
    private static void mergeOfTwoSortedArrAndCountSmaller(int[] nums, int left, int mid, int right, int[] indexes, int[] temp, int[] res) {
        for (int i = left; i <= right; i++) {
            temp[i] = indexes[i];
        }

        //左边指针
        int i = left;
        //右边指针
        int j = mid + 1;
        //k是本次排序的区间，即对left到right区间的值进行排序
        for (int k = left; k <= right; k++) {
            //左边已经没有元素可排序了
            if (i > mid) {
                //将右边直接入排序序列，排序的是索引的位置。不改变原数组
                indexes[k] = temp[j];
                //右边指针右移到下一个元素
                j++;
                //右边已经没有元素可排序了
            } else if (j > right) {
                //将左边的元素入排序序列，排序的是索引位置
                indexes[k] = temp[i];
                //左边元素指针右移
                i++;
                //因为左边还有，而右边没有元素了，说明右边的元素都早已进入排序序列，
                //说明右边的这些元素比当前元素小，而这些元素正是当前元素的逆序数。即此时整个右边都是该元素的逆序数
                res[indexes[k]] += (right - mid);

                //当左边元素小于右边元素
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                // 注意：这里是 <= ，保证稳定性
                //入排序序列，对索引排序
                indexes[k] = temp[i];
                //左边指针右移
                i++;
                //右边本次归并已经进入排序序列的都是该元素的逆序数，所以进行统计
                res[indexes[k]] += (j - mid - 1);
            } else {
                //右边的大于左边的，不统计逆序数，直接入排序序列，右边指针右移
                indexes[k] = temp[j];
                j++;
            }
        }
    }
}
