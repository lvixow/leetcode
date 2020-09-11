package com.xiaoxiang.search;

/**
 * author:w_liangwei
 * date:2020/9/11
 * Description: 搜索插入位置 LeetCode 35
 */
public class SearchInsertIndex {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int index = searchInsert(nums, 2);
        System.out.println(index);
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        //当left和right没有相遇则继续查找
        while (left < right) {
            int mid = (left + right) / 2;
            //当target大于nums[mid]则表明mid处的值不可能是查找值，而插入值的话肯定比mid处的大，则至少从mid+1开始
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                //如果是查找值则正好和mid处相等直接返回
                //如果是插入值则表明target比mid处的值小，正好插入位置就是mid所在的位置
                right = mid;
            }
        }
        //最终left和right相等才会跳出上边的循环，所以此时返回哪个都可以
        return left;
    }
}
