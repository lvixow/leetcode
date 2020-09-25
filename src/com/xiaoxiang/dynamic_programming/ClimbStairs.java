package com.xiaoxiang.dynamic_programming;


/**
 * author:w_liangwei
 * date:2020/9/25
 * Description: 爬楼梯 LeetCode 70
 * 第i层的爬法 = 第i-1层爬法 + 第i-2层爬法
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int climbStairs = climbStairs(1);
        System.out.println(climbStairs);
    }

    public static int climbStairs(int n) {
        //回溯法，会超时
//        if (n == 1 || n == 2) {
//            return n;
//        }
        //从后往前，导致了大量计算的重复
//        return climbStairs(n -1) + climbStairs(n -2);


        //保存不同数量的台阶对应的走法，供后续计算使用
        //空出索引为0的位置，使得第i个台阶的走法就对应数组的索引为i。因为输入至少为1，加2正好满足空出0的位置和两个初始值的位置
        int[] dp = new int[n + 2];
        //当台阶为1或2的时候只有1种和2种走法
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            //从第三阶开始符合：第i层的爬法 = 第i-1层爬法 + 第i-2层爬法
            //此处不是递归调用此方法，而是直接从数组中直接获取i-1和i-2的值
            dp[i] = dp[i-1] + dp[i-2];
        }
        //返回第n个台阶的走法
        return dp[n];
    }
}
