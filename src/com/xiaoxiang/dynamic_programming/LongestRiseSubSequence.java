package com.xiaoxiang.dynamic_programming;

import java.util.Arrays;

/**
 * @Date 2020/9/28 6:46
 * @Auther 梁伟
 * @Description 最长上升子序列 LeetCode 300
 *
 * 状态转移方程：
 * 遍历到 nums[i] 时，需要把下标 i 之前的所有的数都看一遍；
 * 只要 nums[i] 严格大于在它位置之前的某个数，那么 nums[i] 就可以接在这个数后面形成一个更长的上升子序列；
 * 因此，dp[i] 就等于下标 i 之前严格小于 nums[i] 的状态值的最大者 +1+1+1。
 *
 */
public class LongestRiseSubSequence {
    public static void main(String[] args) {
        int[] nums = {1,3,6,7,9,4,10,5,6};
        //1,3,6,7,9,10
        int length = lengthOfLIS(nums);
        System.out.println(length);
    }

    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        //每个以nums[i]结尾的最长子序列，最短的时候是只包含自己，所以长度为1
        //如果nums[i]没有比任何i之前的元素大，则最大上升子序列只包好自己
        Arrays.fill(dp, 1);
        //计算每一个dp[i]
        for (int i = 1; i < len; i++) {
            //比较当前数字nums[i]是否比i之前的哪个数字大，大的话就获取该数字的dp[j]然后加上自己构成dp[i]的上升子序列
            //可能和不同的dp[j]构成多个不同的上升子序列，所以需要比较哪个最长
            for (int j = 0; j < i; j++) {
                //比以nums[i]结尾的上升子序列大，则说明可以构成新的上升子序列
                if (nums[i] > nums[j]) {
                    //比较更子序列是否比原来的长度要长，长的话才更新
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        //找到dp中最大的即为最大子序列长度
        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
