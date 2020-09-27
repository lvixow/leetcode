package com.xiaoxiang.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author:w_liangwei
 * date:2020/9/27
 * Description: 三角形最小路径和 LeetCode 120
 * 状态转移方程：dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]
 */
public class TriangleMinPathSum {
    public static void main(String[] args) {
        Integer[][] triangle = {{2},
                                {3,4},
                                {6,5,7},
                                {4,1,8,3}};
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < triangle.length; i++) {
            List<Integer> list1 = Arrays.asList(triangle[i]);
            list.add(list1);
        }
        int minimumTotal = minimumTotal(list);
        System.out.println(minimumTotal);
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        /*//dp[i][j]代表当前第i层第j个数是该层的最佳位置。行列去除0的位置
        int n = triangle.size();
        //多初始化一行一列，使得使用状态转移方程时数组不可能越界，有了一个保底的值
        int[][] dp = new int[n + 1][n + 1];
        //从最后一行开始推
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                //初始化dp的时候，多一个0行和0列，正好使得i+1和j+1不用越界，使用一个比triangle多一行的dp但是最佳值为0，所以不会对结果有影响，还能够防止数组越界
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        //由于是倒着推过来的，最顶部就是最后的和
        return dp[0][0];*/



        //对dp进行空间压缩
        int n = triangle.size();
        //因为每次计算dp[i][j]时只和下一行有关，所以可以只保留上次计算出来的那一行
        int[] dp = new int[n + 1];
        //从最后一行开始推
        for (int i = n - 1; i >= 0; i--) {
            //遍历每一层的每一个数字，将当前层每个数字对应的最优解写到dp中，位置与该数字在当前层的原位置一致
            /*
            dp更新过程
                    1.由于默认最后一层后还有保底的一层都是0，所以在最后一层dp的最优解就是4,1,8,3
                    2.当到达第三层，dp = {7,6,10,3}
                    3.当到达第二层，dp = {9,10,10,3}
                    4.当到达第一层，dp = {11,10,10,3}
            * */
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        //由于是倒着推过来的，最顶部就是最后的和
        return dp[0];
    }
}
