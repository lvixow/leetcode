package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/22
 * Description: offer 14
 */
public class CuttingRope {
    public static void main(String[] args) {
        int length = 10;
        //因为剪的位置只能是从1开始到绳长-1结束，所以设置dp空间为这么大
        int[] dp = new int[length + 1];
        //0 不是正整数，1 是最小的正整数，0 和 1 都不能拆分，因此 dp[0]=dp[1]=0
        int max = 0;
        //计算每一个dp[i]的值，从dp[2]开始
        for (int i = 2; i < length; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(i * (i-j), j * dp[i-j]));
            }
        }
        System.out.println(dp[length]);
    }
}