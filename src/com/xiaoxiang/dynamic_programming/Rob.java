package com.xiaoxiang.dynamic_programming;

/**
 * author:w_liangwei
 * date:2020/9/25
 * Description: 打家劫舍 LeetCode 198
 *
 * 选：当前房间的金额 + 第i-2个房间之前的最大金额
 * 不选：第i-1个房间之前的最大金额
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int rob = rob(nums);
        System.out.println(rob);
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        //当只有一个房间时，则偷该房间为最高
        dp[0] = nums[0];
        //当有2个房间时，则只能在其中选一个金额多的偷
        dp[1] = Math.max(nums[0], nums[1]);
        //从第3个房间开始,面临选或者不选的情况
        //选：当前房间的金额 + 第i-2个房间之前的最大金额
        //不选：第i-1个房间之前的最大金额
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[nums.length -1];
    }
}
