package com.xiaoxiang.search;

import java.util.Arrays;

/**
 * @Date 2020/9/24 5:54
 * @Auther 梁伟
 * @Description 火柴拼正方形  LeetCode 473
 */
public class MatchSquare {
    public static void main(String[] args) {
        int[] nums = {3,3,3,3,4};
        boolean makesquare = makesquare(nums);
        System.out.println(makesquare);
    }

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
        if (total % 4 != 0) {
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
    private static boolean backtrack(int[] nums, int target, int[] temp, int index) {
        //当所有边都满足时返回
        if (temp[0] == target && temp[1] == target && temp[2] == target && temp[3] == target) {
            return true;
        }
        if (index > nums.length -1) {
            return false;
        }
        //加入当前火柴
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] + nums[index] <= target) {
                temp[i] = temp[i] + nums[index];
                backtrack(nums, target, temp, index -1);
            }
        }
        return backtrack(nums, target, temp, index);
    }
}
