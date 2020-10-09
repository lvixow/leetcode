package com.xiaoxiang.high_level_data_structures;

/**
 * @Date 2020/10/10 6:49
 * @Auther 梁伟
 * @Description 朋友圈 LeetCode 547
 */
public class FindCircleNum {

    public DisjointSet disjointSet;

    public FindCircleNum(int num) {
        this.disjointSet = new DisjointSet(num);
    }

    public static void main(String[] args) {
        int[][] m = {{1,1,0},
                     {1,1,0},
                     {0,0,1}};
        //总共有3个人，即3行3列
        FindCircleNum a = new FindCircleNum(3);
        int circleNum = a.findCircleNum(m);
        System.out.println(circleNum);
    }

    public int findCircleNum(int[][] M) {
        for (int i = 0; i < M.length; i++) {
            //内循环直接从i开始就可以了，因为是对称矩阵啊
            for (int j = 0; j < i; j++) {
                //存在朋友关系则合并到同一集合
                if (M[i][j] == 1) {
                    disjointSet.union(i, j, disjointSet.parent, disjointSet.rank);
                }
            }
        }
        return disjointSet.count;
    }
}
