package com.xiaoxiang.recursion_recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/6 18:11
 * @Auther 梁伟
 * @Description n皇后 LeetCode51
 */
public class SolveNQueens {
    public static void main(String[] args) {
        List<List<String>> solveNQueens = solveNQueens(4);
        System.out.println(solveNQueens);
    }

    /**
     * @auther 梁伟
     * @Description 依次放置每一行的皇后，然后更新不可以放置皇后的位置。接下来放置下一行皇后，
     * 当该行全部被攻击无法放置皇后，则进行回溯
     * @Date 2020/9/6 18:12
     * @Param [n]
     * @return java.util.List<java.util.List<java.lang.String>>
     **/
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        //棋盘
        char[][] chess = new char[n][n];
        //初始化棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }

        //从第0行开始放置皇后
        dfs(0, chess, result);
        return result;
    }

    /**
     * @auther 梁伟
     * @Description 深度递归放置皇后
     * @Date 2020/9/6 18:43
     * @Param [row, chess, result] 当前放置第几行，棋盘，结果集
     * @return void
     **/
    private static void dfs(int row, char[][] chess, List<List<String>> result) {
        //所有行填满，添加结果
        if (row == chess.length) {
            result.add(null);
            return;
        }

        //对当前行尝试放置每一列
        for (int column = 0; column < chess.length; column++) {
            if (valid(chess, row, column)) {
                //放置皇后
                chess[row][column] = 'Q';
                //放下一行
                dfs(row + 1, chess, result);
                //回溯
                chess[row][column] = '.';
            }
        }
    }

    /**
     * @auther 梁伟
     * @Description 用于判断指定位置是否可以放置皇后
     * @Date 2020/9/8 7:30
     * @Param [chess, row, column] 棋盘，行，列
     * @return boolean
     **/
    private static boolean valid(char[][] chess, int row, int column) {
        //因为是一行一行往下放，所以只有列和斜对角出现攻击，行不会被攻击
        //列攻击检查
        for (int i = 0; i < row; i++) {
            if (chess[i][column] == 'Q') {
                return false;
            }
        }




        return false;
    }

    public void preOrder() {

    }
}
