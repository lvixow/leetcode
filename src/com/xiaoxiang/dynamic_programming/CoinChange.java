package com.xiaoxiang.dynamic_programming;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2020/9/25
 * Description: 硬币找零 LeetCode 322
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
//        int[] coins = {2};
        int coinChange = coinChange(coins, 11);
        System.out.println(coinChange);
    }

    public static int coinChange(int[] coins, int amount) {
        //初始化dp，dp[i]代表金额为i时的最优解
        int[] dp = new int[amount + 1];
        //填充dp的默认值为amount + 1,因为最小的输入面额为1元，所以当前金额所需的最大钞票张数就是该金额都是被1元组成的，而amount + 1就是一个不可能的解，从而来判断该金额是否有最优解
        Arrays.fill(dp, amount + 1);
        //金额0的最优解是0
        dp[0] = 0;
        //获得从1到i之间所有的最优解，从而推出i的最优解
        for (int i = 1; i <= amount; i++) {
            //获取不同面值下的最优解，逐渐更新最优解，找到最小值
            for (int j = 0; j < coins.length; j++) {
                //当前金额没有该钞票面额大，则无法组成该金额，跳过该钞票
                if (i - coins[j] < 0) {
                    continue;
                }
                //递推方程：dp[i] = dp[i-coins[j]] +1。即当前金额的最优解 = 选择一张钞票 + 剩余面额的最优解，如 10 = (2)1 + dp[8], 10 = (5)1 + dp[5]
                //更新最优解
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        //当dp[i]是初始化给的不可能的值时，说明没有最优解。返回-1，否则返回最优解
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
