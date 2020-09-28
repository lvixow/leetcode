package com.xiaoxiang.dynamic_programming;

/**
 * author:w_liangwei
 * date:2020/9/28
 * Description: 最小路径和 LeetCode 64
 *
 * 状态转移方程：走到当前单元格(i,j) 的最小路径和 = “从左方单元格 (i−1,j) 与 从上方单元格(i,j−1)走来的两个最小路径和中较小的”+ 当前单元格值 grid[i][j]
 * grid[row][column] = Math.min(grid[row-1][column], grid[row][column -1]) + grid[row][column];
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},
                        {1,5,1},
                        {4,2,1}};
        /* 该数组对应生成的dp数组
            {{1,4,5},
            {2,7,6},
            {6,8,7}}
        */
        int minPathSum = minPathSum(grid);
        System.out.println(minPathSum);
    }

    public static int minPathSum(int[][] grid) {
        //dp[i][j]代表走到当前位置时的最小路径和，此处不需要申请dp空间，只需要使用原来的grid数组即可
        //双层循环控制了左边和上边越界的问题，也遍历生成了每个dp对应的最小路径和
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                //在左上角,由于最短路径和与原数组值一样，无需修改
                if (row == 0 && column == 0) {
                    //什么都不需要做
                } else if (row == 0) {
                    //当前位置在第一行，只能从左边过来
                    grid[row][column] = grid[row][column -1] + grid[row][column];
                } else if (column == 0) {
                    //当前位置在第一列，只能从上边过来
                    grid[row][column] = grid[row -1][column] + grid[row][column];
                } else {
                    //可以从上方或左边
                    grid[row][column] = Math.min(grid[row-1][column], grid[row][column -1]) + grid[row][column];
                }
            }
        }
        //右下角的位置就是我们要到达的位置，获取该位置最优解
        return grid[grid.length -1][grid[0].length -1];
    }
}
