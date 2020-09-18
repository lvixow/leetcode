package com.xiaoxiang.binarySearch_binaryTree;

/**
 * @Date 2020/9/13 10:31
 * @Auther 梁伟
 * @Description leetcode 33
 */
public class SearchRotateArray {
    public static void main(String[] args) {
        int[] nums = {3,1};
        int search = search(nums, 1);
        System.out.println(search);
    }


    /**
     * @auther 梁伟
     * @Description 每次二分将数组分为一个有序的和一个无序的，然后在有序区间内查找。逐渐缩小有序区间
     * @Date 2020/9/13 16:58
     * @Param [nums, target]
     * @return int
     **/
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
                //前半段有序,由于mid是向下取整，所以mid有可能和left相同，所以需要加等号
            } else if (nums[mid] >= nums[left]) {
                //target在0到mid之间，则在前半段查找，反之在后半段查找
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    //后半段查找
                    left = mid + 1;
                }
                //后半段有序
            } else {
                //target在mid到最后一个元素之间，则在后半段查找，反之在前半段查找
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }
}
