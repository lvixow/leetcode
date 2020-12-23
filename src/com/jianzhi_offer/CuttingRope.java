package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/22
 * Description: offer 14
 *
 * 解法
 * 1. 动态规划从底向上
 * 2. 记忆化递归从上向下
 * 3. 使用数学的思想，即找到一个正整数n，多个正整数的和等于n时，他们的积是最大的。涉及到算术几何不等式，
*     如果要考虑清楚这个问题，可能需要证明此不等式，是一个难点，但也是效率最高的。具体参考LeetCode网站题解
 *
 * 本题使用了动态规划求解
 *
 */
public class CuttingRope {
    public static void main(String[] args) {
        int length = 8;
        System.out.println(cuttingRope(length));
    }

    public static int cuttingRope(int n) {
        //为了保证n的值就在索引为n的位置，所以空出0的位置，从1开始放dp
        int[] dp = new int[n+1];
        //长度为0没法剪，长度为1也是没法剪，因为必须在整数的位置剪，即至少剪的位置是绳子的中间部位
        if (n < 2) {
            return 0;
        }
        //当长度为2时只能从1的位置剪一下
        if (n == 2) {
            return 1;
        }
        //当长度为3，可以剪成1,1,1也可以剪成1,2两种，所以最大就是2
        if (n == 3) {
            return 2;
        }
        //长度小于4时，不剪就是最大值。此处之所以和上边判断中返回的值不一样是因为，返回值是至少剪一下得到的结果
        //当子段绳子的长度是1,2,3,4时，必定不能再剪，再剪会使得值变小，所以剪的这下必然是在另外一段发生的，所以满足题目至少剪一下的要求
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        //n大于5以后，根据前边已经解决的子问题，递推接下来规模更大的问题
        //状态转移方程：dp[i] = dp[j] + dp[i -j]
        for (int i = 5; i <= n ; i++) {
            //每一个dp[i]位置的计算都需要依赖于前边已经计算出来的值，每次枚举出一种剪的方法，在所有的剪的方法中取最大值。
            //如dp[5] = dp[1] + dp[4] = dp[2] + dp[3]，再往下3,2和4,1又重复了原来的剪法，所以每一个dp[i]的计算都只需要剪到i的一半就可以得到最大值
            for (int j = 1; j <= i/2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i-j]);
            }
        }
        return dp[n];
    }
}