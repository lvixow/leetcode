package com.jianzhi_offer;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2021/2/4
 * Description: offer 42
 *
 * 解法
 * 1. 暴力法：双层循环枚举所有数组，如索引（1,1），（1,2），（1,3）（1,4）。。。（2,2），（2,3），（2,4）。。。然后计算每一个数组的和，通过比较获得最大值，复杂度o(n^2)
 * 2. 动态规划：
 *              dp[j]表示所有以索引j结束的数组, 这些数组中和的最大值就是dp[j]。如以j=3组成的数组，（0,3），（1,3），（2,3），（3,3），然后比较这些数组哪个数组的和最大，这个就是dp[3]的值
 *              dp[j] = dp[j-1] + nums[j]  dp[j-1] > 0
 *              dp[j] = nums[j]            dp[j-1] <= 0
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSubArray = maxSubArray(arr);
        System.out.println(maxSubArray);
    }

    public static int maxSubArray(int[] nums) {
        //未空间优化
//        int[] dp = new int[nums.length - 1];
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (dp[i - 1] > 0) {
//                dp[i] = dp[i - 1] + nums[i];
//            } else {
//                dp[i] = nums[i];
//            }
//        }

        //空间优化，dp数组可以使用原数组
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i - 1] > 0) {
//                nums[i] = nums[i - 1] + nums[i];
//            } else {
//                nums[i] = nums[i];
//            }
//        }

        //继续优化
//        for (int i = 1; i < nums.length; i++) {
//            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
//        }

        //单次循环出结果
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
