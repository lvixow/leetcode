package com.xiaoxiang.dynamic_programming;

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
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        //金额0的最优解是0
        dp[0] = 0;
        //获得从1到i之间所有的最优解，从而推出i的最优解
        for (int i = 1; i <= amount; i++) {
            //获取不同面值下的最优解，逐渐更新最优解，找到最小值
            for (int j = 0; j < coins.length; j++) {
                //i-coins[j]保证当前金额至少比一张面额的金额大（也保证了索引不越界） 并且 当前最优解所依赖的上一个的最优解已经计算出来
                if (i-coins[j] >= 0 && dp[i-coins[j]] != -1) {
                    if (dp[i] == -1 || dp[i] > dp[i-coins[j]] + 1) {
                        //递推方程：dp[i] = dp[i-coins[j]] +1。即当前金额的最优解 = 选择一张钞票 + 剩余面额的最优解，如 10 = (2)1 + dp[8], 10 = (5)1 + dp[5]
                        dp[i] = dp[i-coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }
}
