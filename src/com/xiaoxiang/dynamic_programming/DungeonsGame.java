package com.xiaoxiang.dynamic_programming;

/**
 * author:w_liangwei
 * date:2020/9/28
 * Description: 地下城游戏 LeetCode 174
 *
 * 从上到下，从左到右正序只能过获得最多的血量，由最多血量也无法推出至少需要的血量。所以我们从后往前推
 * 状态转移方程：dp[i][j] = max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
 */
public class DungeonsGame {
    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3},
                           {-5, -10,1},
                           {10, 30, -5}};
        int minimumHP = calculateMinimumHP(dungeon);
        System.out.println(minimumHP);
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int lastRow = dungeon.length;
        int lastColumn = dungeon[0].length;
        //dp[i][j]表示从i,j这个位置到达右下角所需的最小血量
        int[][] dp = new int[lastRow][lastColumn];
        //初始化最后一个位置的最小血量要求
        dp[lastRow-1][lastColumn-1] = dungeon[lastRow-1][lastColumn-1] >= 0 ? 1 : Math.abs(dungeon[lastRow-1][lastColumn-1]) + 1;
        for (int row = lastRow - 1; row >= 0; row--) {
            for (int column = lastColumn -1; column > 0; column--) {
                //跳过右下角最后一个位置，已经初始化过了
                if (row == lastRow -1 && column == lastColumn -1) {
                    continue;
                }
                if (dungeon[row][column] >= 0) {
                    //-----dungeon[row+1][column]-----dungeon[row][column+1]-----dp[row][column]，计算绝对值，绝对值小则该血量最接近dp[row][column]，需要补充的血量最小
                    Math.min(dp[row+1][column], dp[row][column+1]);
                    dp[row][column] = 1;
                } else if (dungeon[row][column] < 0){

                }
            }
        }



        return 0;
    }
}
