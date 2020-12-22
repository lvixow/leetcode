package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/21
 * Description: offer 13
 */
public class MovingCount {
    int row, col, k;
    boolean[][] visited;

    public int movingCount(int row, int col, int k) {
        this.row = row; this.col = col; this.k = k;
        this.visited = new boolean[row][col];
        return dfs(0, 0, 0, 0);
    }

    public static void main(String[] args) {
        MovingCount movingCount = new MovingCount();
        int count = movingCount.movingCount(38, 15, 9);
        System.out.println(count);
    }

    /**
     *
     * @param row
     * @param col
     * @param rowSum 行坐标的和
     * @param colSum 列坐标的和
     * @return
     */
    private int dfs(int row, int col, int rowSum, int colSum) {
        //超出搜索范围，已经搜索过，坐标值不符合要求则返回当前位置的计数为0
        if (row >= this.row || col >= this.col || visited[row][col] || rowSum + colSum > k) return 0;
        visited[row][col] = true;
        //分别计算下一个行坐标的和 和 列坐标的和
        //如果最后一位是0说明发生了进位，那么这时个位是9，当加1后变成了0，减少了9。而加1后十位进了1，那么总体减少了8
        //如原先是19，加1变成20，新的值 = 1 + 9 - 8 = 2
        int nextRowSum = (row + 1) % 10 != 0 ? rowSum + 1 : rowSum - 8;
        int nextColSum = (col + 1) % 10 != 0 ? colSum + 1 : colSum - 8;
        //根据可达解的结构和连通性，可以发现仅通过向右和向下移动，访问所有可达解
        int count = 1 + dfs(row + 1, col, nextRowSum, colSum) + dfs(row, col + 1, rowSum, nextColSum);
        return count;
    }
}
