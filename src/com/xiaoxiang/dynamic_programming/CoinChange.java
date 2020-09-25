package com.xiaoxiang.dynamic_programming;

/**
 * author:w_liangwei
 * date:2020/9/25
 * Description: 硬币找零 LeetCode 322
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int coinChange = coinChange(coins, 11);
        System.out.println(coinChange);
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount < coins[0]) {
            return -1;
        }
        int[] dp = new int[amount];
        //金额0的最优解是0
        dp[0] = 0;
        for (int i = 1; i < amount; i++) {
            if (i < coins[0]) {
                dp[i] = 0;
                continue;
            }
        }
        return 0;
    }
}
