package com.xiaoxiang.dynamic_programming;

/**
 * author:w_liangwei
 * date:2020/9/25
 * Description: 最大子序和 LeetCode 53
 *
 */
public class MaxSubArraySum {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSubArray = maxSubArray(nums);
        System.out.println(maxSubArray);
    }

    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //当dp[i -1] > 0,则dp[i] = dp[i-1] + nums[i]
            //当dp[i-1] <= 0,则dp[i] = nums[i]
            //递推方程是dp[i] = dp[i-1] + nums[i]，必须保证子序列以nums[i]结尾
            dp[i] = Math.max(nums[i], dp[i] = dp[i -1] + nums[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
