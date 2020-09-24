package com.xiaoxiang.search;

import java.util.Arrays;

/**
 * @Date 2020/9/24 5:54
 * @Auther 梁伟
 * @Description 火柴拼正方形  LeetCode 473
 */
public class MatchSquare {
    public static void main(String[] args) {
//        int[] nums = {3,3,3,3,4};
//        int[] nums = {1,1,2,2,2};
        int[] nums = {10,10,10,10,7,7,7,7,7,7,7,7,6,6,6,6};
        boolean makesquare = makesquare(nums);
        System.out.println(makesquare);
    }

    private static boolean ans = false;

    /**
     * @auther 梁伟
     * @Description
     * 1.判断数组和是不是4的倍数，如果不是的话，那一定不能拼成正方形
     * 2.若有一个数大于数组和/4，那么也一定不能拼成正方形
     * @Date 2020/9/24 6:41
     * @Param [nums]
     * @return boolean
     **/
    public static boolean makesquare(int[] nums) {
        //总长度必须是4的倍数
        int total = 0;
        for (int num : nums) {
            total = total + num;
        }
        if (nums.length < 4 || total % 4 != 0) {
            return false;
        }
        //排序为了每次可以拿出最大的边，方便构建正方形
        Arrays.sort(nums);
        //如果其中一根火柴大于边的总长度，也不能构成正方形
        int target = total/4;
        if (nums[nums.length -1] > target) {
            return false;
        }
        //火柴摆放的长度由大到小
        return backtrack(nums, target, new int[4], nums.length -1);
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/9/24 6:58
     * @Param [nums, target, temp, index] 总火柴，单边长度，每边的总长度，当前要加入的火柴在nums中的索引
     * @return boolean
     **/
    private static boolean backtrack(int[] nums, int target, int[] bucket, int index) {
        //当所有火柴放完，所有边满足长度要求
        if (index == -1) {
            return bucket[0] == target && bucket[1] == target && bucket[2] == target && bucket[3] == target;
        }
        //尝试从每条边加入当前火柴
        for (int i = 0; i < 4; i++) {
            //只有当加入该火柴后不超过边长才能加入
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            //加入火柴
            bucket[i] = bucket[i] + nums[index];
            //当返回true，说明所有火柴放完了，并且也组成了正方形，此时应该停止摆放，也不需要回溯
            if (backtrack(nums, target, bucket, index -1)) {
                return true;
            }
            //当前火柴摆放的边不合适，重新摆放
            bucket[i] = bucket[i] - nums[index];
        }
        //上一根摆放的不合适，重新摆放
        return false;
    }
}
