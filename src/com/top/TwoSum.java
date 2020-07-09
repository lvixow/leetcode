package com.top;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author:w_liangwei
 * date:2020/7/8
 * Description:
 */
public class TwoSum {
    public static void main(String[] args) {
        int target = 26;
        int[] nums = {2, 7, 11,15};
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        //存放<数值---索引>用于减少查找次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int anotherNum = target - nums[i];
            if (map.containsKey(anotherNum)) {
                return new int[]{i, map.get(anotherNum)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}