package com.xiaoxiang.dynamic_programming;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2020/9/28
 * Description: 地下城游戏 LeetCode 174
 *
 * 为什么倒推：
 * 从上到下，从左到右正序只能过获得最多的血量，由最多血量也无法推出至少需要的血量。而从后往前推只要我们保证到达终点时只剩下数量为1的血量，
 * 依次倒推就可以获得初始位置需要的最少血量
 *
 * 推导状态转移方程：
 * 当我们想从i,j到达下一个位置，下一个位置在右边或者是下边，我们肯定会选一个需要血量最少的位置，这样才会使当前位置的最少血量最小，即min(dp[i+1][j], dp[i][j+1])
 * dp[i+1][j], dp[i][j+1]肯定是正的，因为是一个该位置要达到终点的最少血量，如果为负或者是0根本就到达不了
 *
 *                                                                             结果大于0          说明当前位置提供的血量满足不了到达下个位置，需要对差的血量进行补齐
 *                  正：min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]
 * dungeon[i][j]                                                               结果小于等于0      说明dungeon[i][j]会加血，并且加的血量足够比到达下个位置所需血量min(dp[i+1][j], dp[i][j+1])大，所以此时只需要一个血就可以到达下个位置
 *
 *                  负：-dungeon[i][j] + min(dp[i+1][j], dp[i][j+1])          -dungeon[i][j]将当前位置所需血量补齐，加上到达下个位置所需血量，就是最少血量
 *
 * 状态转移方程：dp[i][j] = max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
 *
 *
 * 还可以使用dfs + 记忆化寻找所有路径中最优路径
 */
public class DungeonsGame {
    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3},
                           {-5, -10,1},
                           {10, 30, -5}};
        int miniNumHp = calculateMinimumHP(dungeon);
        System.out.println(miniNumHp);
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int lastRow = dungeon.length;
        int lastColumn = dungeon[0].length;
        //dp[i][j]表示从i,j这个位置到达右下角所需的最小血量。多初始化一行一列不用判断数组是否越界
        int[][] dp = new int[lastRow +1][lastColumn +1];
        //初始化dp为最大值，因为dp中的每个位置都要更新为一个至少需要血量，也就是会取一个最小值。初始化最大值利于比较进行dp的更新
        for (int[] rowArr : dp) {
            Arrays.fill(rowArr, Integer.MAX_VALUE);
        }
        //由于是倒着推，所以前一个位置在当前位置的右边或者是下边。由于最后剩的血量最少为1，才算通过，所以初始化最后这两个位置可能到达的位置为1
        dp[lastRow][lastColumn-1] = 1;
        dp[lastRow -1][lastColumn] = 1;
        //开始倒推
        for (int row = lastRow -1; row >= 0; row--) {
            for (int column = lastColumn -1; column >= 0; column--) {
                //状态转移方程
                int minHp = Math.min(dp[row][column +1], dp[row +1][column]) - dungeon[row][column];
                //该语句与下边的if判断等价，因为当minHp为负数，此时1肯定大，所以会取1。当minHp为正数时分开讨论，当为1时，去谁都一样。当minHp大于1时，则会取minHp。
                dp[row][column] = Math.max(minHp, 1);
//                if (minHp <= 0) {
//                    dp[row][column] = 1;
//                } else {
//                    dp[row][column] = minHp;
//                }
            }
        }
        //打印dp数组
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[0][0];
    }
}
