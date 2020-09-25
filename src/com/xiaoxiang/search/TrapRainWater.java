package com.xiaoxiang.search;

import java.util.PriorityQueue;

/**
 * author:w_liangwei
 * date:2020/9/24
 * Description: 收集雨水 LeetCode 407
 *
 * 要想兜住雨水，必须四周比中心点高，首先我们将最外围设置为四周，将四周放入小顶堆，使用BFS进行搜索，每次从堆中
 * 弹出四周最小的高度，当四周最小高度比中心点高则可以积水，否则无法积水
 *
 * 先把最外围的一圈作为围栏， 选择一个最低的围栏， 如果这个围栏的邻节点都比它大， 此围栏可删除，邻节点作为新的围栏；
 * 如果邻节点比它小， 那么邻节点可储蓄的水为 二者高度之差， 此时在邻节点设置围栏，高度为当前围栏高度即可。
 */
public class TrapRainWater {
    public static void main(String[] args) {
        int[][] heightMap = {{1,4,3,1,3,2},
                            {3,2,1,3,2,4},
                            {2,3,3,2,3,1}};
        int rainWater = trapRainWater(heightMap);
        System.out.println(rainWater);
    }

    static class Cell implements Comparable<Cell> {
        private int row;
        private int column;
        private int height;

        public Cell(int row, int column, int height) {
            this.row = row;
            this.column = column;
            this.height = height;
        }


        @Override
        public int compareTo(Cell other) {
            if (this.height == other.height) {
                return 0;
            }
            if (this.height < other.height) {
                return -1;
            }
            return 1;
        }
    }


    public static int trapRainWater(int[][] heightMap) {
        //记录该位置是否被访问过
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        //使用优先队列每次取出四周最小高度的位置
        PriorityQueue<Cell> cellPriorityQueue = new PriorityQueue<>();

        //将heightMap最外层的四周加入到已访问
        //将第一行和最后一行加入到已访问
        int lastRowIndex = heightMap.length -1;
        for (int i = 0; i < heightMap[0].length; i++) {
            visited[0][i] = true;
            visited[lastRowIndex][i] = true;
            cellPriorityQueue.add(new Cell(0, i, heightMap[0][i]));
            cellPriorityQueue.add(new Cell(lastRowIndex, i, heightMap[lastRowIndex][i]));
        }

        //将第一列和最后一列加入到已访问
        int lastColumnIndex = heightMap[0].length -1;
        for (int i = 0; i < heightMap.length; i++) {
            visited[i][0] = true;
            visited[i][lastColumnIndex] = true;
            cellPriorityQueue.add(new Cell(i, 0, heightMap[i][0]));
            cellPriorityQueue.add(new Cell(i, lastColumnIndex, heightMap[i][lastColumnIndex]));
        }

        //记录积水总量
        int result = 0;
        int[][] directionArr = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        //进行BFS由外往里进行收缩
        while (!cellPriorityQueue.isEmpty()) {
            Cell current = cellPriorityQueue.poll();
            int row = current.row, column = current.column, height = current.height;
            //向四周寻找
            for (int i = 0; i < 4; i++) {
                //获得新位置的行列
                int r = row + directionArr[i][0];
                int c = column + directionArr[i][1];
                //判断新位置是否越界，和是否访问过。排除掉最外围的四周，因为一开始已经加入到队列
                if (r > 0 && c > 0 && r < lastRowIndex && c < lastColumnIndex && !visited[r][c]) {
                    //如果新位置比原来的点低，则说明可以积水
                    result = result + Math.max(0, height - heightMap[r][c]);
                    //更新新位置的高度为积水后的高度
                    heightMap[r][c] = Math.max(height, heightMap[r][c]);
                    //将入队列并标记为已访问
                    cellPriorityQueue.add(new Cell(r, c, heightMap[r][c]));
                    visited[r][c] = true;
                }
            }
        }
        return result;
    }
}
