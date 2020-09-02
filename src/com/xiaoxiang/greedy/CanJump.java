package com.xiaoxiang.greedy;


/**
 * author:w_liangwei
 * date:2020/9/2
 * Description: 跳跃游戏
 */
public class CanJump {
    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {3,2,1,0,4};
        System.out.println(canJump(nums2));
    }


    public static boolean canJump(int[] nums) {
        //用于保存当前索引位置可到达的最大索引位置
        int[] maxIndexArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //当前位置对应一个最大可以跳跃的位置，在这些位置中我们要选择下一跳可以达到最大位置的位置。如[2,3,1,1,4]，i=0可以跳2步，但我们只能选择i=1的位置，因为这个位置对应的跳数可以达到最远
            maxIndexArr[i] = i + nums[i];
        }

        //当前所在位置
        int jump = 0;
        //当前索引对应的可跳跃最大位置
        int maxIndex = maxIndexArr[0];
        //当跳跃的位置不超出数组最大位置或者跳跃的位置不超过本次可跳跃的最大位置才能进行跳跃
        while (jump < nums.length && jump <= maxIndex) {
            //更新当前最大可达位置
            if (maxIndex < maxIndexArr[jump]) {
                maxIndex = maxIndexArr[jump];
            }
            //本次跳跃完成，进行下一次跳跃
            jump++;
        }

        //查看是否到达nums数组末尾，由上一步可知如果jump到达终点，那么jump最后肯定等于nums的length
        return jump == nums.length;
    }
}
