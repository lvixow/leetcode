package com.xiaoxiang.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Date 2020/9/3 6:28
 * @Auther 梁伟
 * @Description 射气球
 */
public class ShootBalloon {

    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        int minArrowShots = findMinArrowShots(points);
        System.out.println(minArrowShots);
    }

    /**
     * @auther 梁伟
     * @Description 使用最少数量的箭射爆气球
     * 输入:[[10,16], [2,8], [1,6], [7,12]]
     * 输出:2
     * @Date 2020/9/3 6:33
     * @Param [points] 每个气球的开始和结束坐标位置，即气球的宽度
     * @return int
     **/
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //对所有气球按照左端点由小到大排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] balloon1, int[] balloon2) {
                int balloon1Begin = balloon1[0];
                int balloon2Begin = balloon2[0];
                return balloon1Begin - balloon2Begin;
            }
        });

        //弓箭手数量
        int sagittaryNum = 1;
        //气球的左端点和右端点
        int begin = points[0][0];
        int end = points[0][1];
        //从第二个气球开始来寻找重合区间
        for (int i = 1; i < points.length; i++) {
            //判断该气球是否与第一个气球宽度有重合，如果有则更新begin和end。因为左端点已经排序，
            //所以默认下一个气球的左端点一定比上一个大。只有当下一个气球的benin小于等于上一个气球的end，则才有重合区间
            if (points[i][0] < end) {
                //更新左端点
                begin = points[i][0];
                //更新右端点
                if (points[i][1] < end) {
                    end = points[i][1];
                }
            } else {
                //没有重合区间，需要增加弓箭手来射击新的区间
                begin = points[i][0];
                end = points[i][1];
                sagittaryNum++;
            }
        }
        return sagittaryNum;
    }
}
