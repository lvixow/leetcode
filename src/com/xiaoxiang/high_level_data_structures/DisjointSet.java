package com.xiaoxiang.high_level_data_structures;

import java.util.Arrays;

/**
 * @Date 2020/10/10 5:51
 * @Auther 梁伟
 * @Description 并查集
 */
public class DisjointSet {

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet(5);
        disjointSet.union(1,2, disjointSet.parent, disjointSet.rank);
        disjointSet.union(2,3, disjointSet.parent, disjointSet.rank);
        System.out.println(disjointSet.findRoot(disjointSet.parent, 1));
    }

    //记录当前节点的父节点，如parent[2] = 3,则2的父节点是3
    int[] parent;
    //记录当前节点所处集合的高度
    int[] rank;
    //记录有几个不同的集合
    int count;

    public DisjointSet(int length) {
        this.parent = new int[length];
        this.rank = new int[length];
        this.count = length;
        //所有节点的父节点初始化为-1
        Arrays.fill(parent, -1);
        //初始化所有集合高度为0
        Arrays.fill(rank, 0);
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/10/10 6:04
     * @Param [parent, x] 标记父节点的数组，待查找父节点的节点
     * @return int
     **/
    public int findRoot(int[] parent, int x) {
        //到达-1时说明找到了根节点
        int xRoot = x;
        while (parent[xRoot] != -1) {
            xRoot = parent[xRoot];
        }
        return xRoot;
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/10/10 6:21
     * @param rank 记录当前节点所处集合的高度
     * @return boolean
     **/
    public boolean union(int x, int y, int[] parent, int[] rank) {
        int xRoot = findRoot(parent, x);
        int yRoot = findRoot(parent, y);
        //根节点相同不需要合并
        if (xRoot == yRoot) {
            return false;
        } else {
            //根据两个集合的高度，将高度小的向高度大的合并，防止树高度的增加
            if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot] = rank[xRoot] + 1;
            }
            //每合并一次，集合个数减1
            count--;
            return true;
        }
    }
}
