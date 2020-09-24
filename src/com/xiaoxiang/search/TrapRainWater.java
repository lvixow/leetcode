package com.xiaoxiang.search;

import java.util.PriorityQueue;

/**
 * author:w_liangwei
 * date:2020/9/24
 * Description: 收集雨水 LeetCode 407
 *
 * 要想兜住雨水，必须四周比中心点高，首先我们将最外围设置为四周，将四周放入小顶堆，使用BFS进行搜索，每次从堆中
 * 弹出四周最小的高度，当四周最小高度比中心点高则可以积水，否则无法积水
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

        int result = 0;




        return result;
    }
}
