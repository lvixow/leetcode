package com.xiaoxiang.search;

import java.util.Arrays;

/**
 * @Date 2020/9/13 9:20
 * @Auther 梁伟
 * @Description LeetCode 34
 */
public class SearchRange {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 9};
        int[] range = searchRange(nums, 9);
        System.out.println(Arrays.toString(range));
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        int firstIndex = -1;
        int lastIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                //向左右分别搜索
                firstIndex = mid;
                lastIndex = mid;
                //查找左端点
                while (firstIndex >= 0 && nums[firstIndex] == target) {
                    firstIndex--;
                }
                //查找右端点
                while (lastIndex <= nums.length -1 && nums[lastIndex] == target) {
                    lastIndex++;
                }
                return new int[]{firstIndex + 1, lastIndex - 1};
            } else if (nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return new int[]{firstIndex, lastIndex};
    }
}
