package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/21
 * Description: offer 12
 */
public class PathExist {
    public static void main(String[] args) {
//        char[][] arr =  {{'A','B','C','E'},
//                         {'S','F','C','S'},
//                         {'A','D','E','E'}};
//        String word = 'ABCCED';
        
        char[][] arr = {{'C','A','A'},
                        {'A','A','A'},
                        {'B','C','D'}};
        String word = "AAB";
        System.out.println(exist(arr, word));
    }

    public static boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                //如果以当前字母开始没有找到符号要求的路径，那么从下一个字母搜索
                if (dfs(board, row, col, word, 0)) return true;
            }
        }
        return false;
    }

    /**
     *
     * @param board
     * @param row
     * @param col
     * @param word  要查找的路径串
     * @param k     当前要查找的字符在Word中的位置
     * @return
     */
    private static boolean dfs(char[][] board, int row, int col, String word, int k) {
        //匹配的位置已经超出数组的边界，该位置搜索失败
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        //当前要匹配的字符与当前位置的字符对不上，则该位置匹配失败
        if (board[row][col] != word.charAt(k)) return false;
        //java中char类型的空字符使用\0表示，如果此位置搜索过了我们就用空字符替换，减少空间使用
        //能匹配上则修改为已经访问过了
        board[row][col] = '\0';
        //如果当前匹配的不是最后字符则继续往下寻找
        if (k == word.length() -1) return true;
        //从当前位置的上下左右4个方向进行深度搜索
        boolean res = dfs(board, row-1, col, word, k+1) || dfs(board, row+1, col, word, k+1)
                    || dfs(board, row, col-1, word, k+1) || dfs(board, row, col+1, word, k+1);
        //深度搜索该路径不符合要求，那么需要将上一个匹配了并已经修改为\0的值再修改为原来的值，供尝试别的路径时使用
        if (!res) board[row][col] = word.charAt(k);
        return res;
    }
}
