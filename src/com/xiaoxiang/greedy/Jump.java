package com.xiaoxiang.greedy;


/**
 * author:w_liangwei
 * date:2020/9/2
 * Description: 跳跃游戏
 */
public class Jump {
    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {3,2,1,0,4};
        System.out.println(canJump(nums1));
        System.out.println(jump(nums1));
    }

    /**
     * @auther 梁伟
     * @Description 是否可以跳跃到数组最后一个位置
     * @Date 2020/9/3 5:43
     * @Param [nums]
     * @return boolean
     **/
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

    /**
     * @auther 梁伟
     * @Description 至少需要跳几次才能到达最后一个位置
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * @Date 2020/9/3 5:44
     * @Param [nums]
     * @return int
     **/
    public static int jump(int[] nums) {
        //数组长度小于2无需跳跃
        if (nums.length < 2) {
            return 0;
        }

        //当前可达最远位置
        int currentMaxIndex = nums[0];
        //用于记录跳跃过程中经过的下一次最大可跳位置
        int preMaxIndex = nums[0];
        //记录最小跳跃次数
        int minJump = 0;
        for (int i = 0; i < nums.length; i++) {
            /*当前要跳跃的步数超过了本次最大跳跃步数，则需要跳跃选取下一个跳跃位置。如[2,3,1,1,4]，第一次只能跳到索引为2的位置，当想
            跳索引为3的位置时，超过了本次的最大可跳步数2。需要选取下一个最大可跳步数，此时则必须发生一次跳跃*/
            if (i > currentMaxIndex) {
                minJump++;
                //更新下一次的最大可跳位置
                currentMaxIndex = preMaxIndex;
            }
            /*跳跃过程中，更新下一次可跳跃的最大位置。用于当必须发生跳跃时，更新currentMaxIndex的值。
            nums[i] + i为当前位置可到达的最大索引*/
            if (nums[i] + i > preMaxIndex) {
                preMaxIndex = nums[i] + i;
            }
        }
        return minJump;
    }
}
