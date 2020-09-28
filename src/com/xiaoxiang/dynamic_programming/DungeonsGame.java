package com.xiaoxiang.dynamic_programming;

/**
 * author:w_liangwei
 * date:2020/9/28
 * Description: 地下城游戏 LeetCode 174
 *
 * 从上到下，从左到右正序只能过获得最多的血量，由最多血量也无法推出至少需要的血量。所以我们从后往前推
 */
public class DungeonsGame {
    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3},
                           {-5, -10,1},
                           {10, 30, -5}};
        int minimumHP = calculateMinimumHP(dungeon);
        System.out.println(minimumHP);
    }

    public static int calculateMinimumHP(int[][] dungeon) {




        return 0;
    }
}
