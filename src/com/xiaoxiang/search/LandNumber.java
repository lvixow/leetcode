package com.xiaoxiang.search;

import java.util.LinkedList;

/**
 * author:w_liangwei
 * date:2020/9/18
 * Description: 岛屿数量 LeetCode 200
 */
public class LandNumber {

    private static int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) {
//        char[][] grid = {{'1','1','0','0','0'},
//                        {'1','1','0','0','0'},
//                        {'0','0','1','0','0'},
//                        {'0','0','0','1','1'}};

        char[][] grid = {{'1'},
                         {'1'}};
        int numIslands = numIslands(grid);
        System.out.println(numIslands);
    }



    public static int numIslands(char[][] grid) {

        //0代表水域，1代表岛屿，2代表岛屿并已经访问过
        //记录岛屿数量
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                //如果当前位置是岛屿，并且未访问过，我们就访问。执行完本地dfs则相连的一片区域全部被搜索完成
                if (grid[row][column] == '1') {
//                    dfs(grid, row, column);
                    bfs(grid, row, column, new LinkedList<>());
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(char[][] grid, int row, int column, LinkedList<Integer> queue) {
        //将当前位置编码为数字，易于存储。加入队列并标记为已访问
        queue.addLast(row * grid[0].length + column);
        grid[row][column] = '2';
        while (!queue.isEmpty()) {
            //标记当前位置为已访问
            Integer curr = queue.removeFirst();
            //获取当前节点所在行列
            int currRow = curr / grid[0].length;
            int currColumn = curr % grid[0].length;
            //访问其他4个方向的邻接点
            for (int i = 0; i < 4; i++) {
                //取得新的位置
                int newRow = currRow + directions[i][0];
                int newColumn = currColumn + directions[i][1];
                //判断新位置的行列是否越界和是否未是未访问的岛屿
                if (newRow >= 0 && newRow < grid.length && newColumn >= 0 && newColumn < grid[0].length && grid[newRow][newColumn] == '1') {
                    queue.addLast(newRow * grid[0].length + newColumn);
                    grid[newRow][newColumn] = '2';
                }
            }
        }
    }

    private static void dfs(char[][] grid, int row, int column) {
        //越界了直接跳出本次递归
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length) {
            return;
        }
        //访问过了该节点或是水域位置则跳出本次递归
        if (grid[row][column] != '1') {
            return;
        }

        //标记当前位置为已访问，并访问四周的位置
        grid[row][column] = '2';
        dfs(grid, row+1, column);
        dfs(grid, row, column+1);
        dfs(grid, row-1, column);
        dfs(grid, row, column-1);
    }



}
