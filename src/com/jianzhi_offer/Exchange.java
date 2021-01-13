package com.jianzhi_offer;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2021/1/13
 * Description: offer 21
 * 使用类似快排的思想，头尾双指针
 */
public class Exchange {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int[] exchange = exchange(arr);
        System.out.println(Arrays.toString(exchange));
    }

    public static int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        //只要指针未相遇则一直找
        while (left < right) {
            //寻找前边的偶数，即头指针当前是奇数就一直往后走
            //指针移动后使用continue来跳到while处判断循环指针是否相遇，指针继续找，直到找到一个偶数
            //当找到偶数后这个判断条件就不成立，left就停到这里了，等待找到奇数后换位置
            if (nums[left] % 2 != 0) {
                left++;
                //使用continue来替代了while内部的另外两个while循环
                continue;
            }
            //寻找后边的奇数，即尾指针偶数当前是偶数则一直往前走
            //和left的移动一样，移动一下判断一下是否相遇，直到找到奇数
            if (nums[right] % 2 == 0) {
                right--;
                continue;
            }
            swap(left, right, nums);
        }
        return nums;
    }

    private static void swap(int left, int right, int[] nums) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
