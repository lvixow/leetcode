package com.xiaoxiang.recursion_recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/6 18:11
 * @Auther 梁伟
 * @Description n皇后 LeetCode51
 *
 * 每次一行放置的位置都有n种选择，总共有n行，所以可以形成一颗n叉树。
 *
 */
public class SolveNQueens {
    public static void main(String[] args) {
        List<List<String>> solveNQueens = solveNQueens(4);
        System.out.println(solveNQueens.size());
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
            result.add(construct(chess));
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
     * 由于是按照行的顺序进行放置，所以肯定不会在同一行。即只需要判断列和斜对角是否被攻击。由于当前要放置的位置下边还没有放置皇后，所以只能是右上角或者左上角被攻击，
     * 即斜对角只需要检查右上角和左上角
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

        //看右上角是否被攻击,即从当前位置的上一行，当前列的右边，也就是右上角第一个斜对角位置开始检查。每次在上一个的检查的位置上上移一行，右移一列，也就是下一个斜对角
        for (int i = row -1, j = column+1; i >= 0 && j < chess.length; i--, j++) {
            //右上角是否有皇后
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        //和右上角一样进行左上角的检查。i代表被检查行，j代表被检查的列。i和j组成一个斜对角的坐标
        for (int i = row -1, j = column -1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }


    /**
     * 将数组转换为List<String>来保存最终结果
     * @param chess 棋盘
     * @return
     */
    private static List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }
}
