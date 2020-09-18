package com.xiaoxiang.search;

/**
 * author:w_liangwei
 * date:2020/9/18
 * Description: 岛屿数量 LeetCode 200
 */
public class LandNumber {
    public static void main(String[] args) {
//        char[][] grid = {{'1','1','0','0','0'},
//                        {'1','1','0','0','0'},
//                        {'0','0','1','0','0'},
//                        {'0','0','0','1','1'}};

        char[][] grid = {{'1'}, {'1'}};
        int numIslands = numIslands(grid);
        System.out.println(numIslands);
    }



    public static int numIslands(char[][] grid) {

        //0代表水域，1代表岛屿，2代表岛屿并已经访问过
        //记录岛屿数量
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                //如果当前位置是岛屿，并且未访问过，我们就访问。执行完本地dfs则相连的一片区域全部被搜索完成
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int x, int y) {
        //越界了直接跳出本次递归
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
            return;
        }
        //访问过了该节点或是水域位置则跳出本次递归
        if (grid[x][y] != '1') {
            return;
        }

        //标记当前位置为已访问，并访问四周的位置
        grid[x][y] = '2';
        dfs(grid, x+1, y);
        dfs(grid, x, y+1);
        dfs(grid, x-1, y);
        dfs(grid, x, y-1);
    }
}
