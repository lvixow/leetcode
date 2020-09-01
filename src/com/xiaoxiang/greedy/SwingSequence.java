package com.xiaoxiang.greedy;

/**
 * @Date 2020/9/1 6:52
 * @Auther 梁伟
 * @Description 摇摆序列
 */
public class SwingSequence {
    public static void main(String[] args) {
        int[] arr1 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        int[] arr2 = {1,1,7,4,9,2,5};
        int maxLength = wiggleMaxLength(arr1);
        System.out.println(maxLength);
    }

    /**
     * @auther 梁伟
     * @Description 每个元素前后的差值一次为正一次为负，形成摇摆
     * 输入: [1,7,4,9,2,5]
     * 输出: 6
     * @Date 2020/9/1 7:04
     * @Param [nums]
     * @return int
     **/
    public static int wiggleMaxLength(int[] nums) {
        //当元素小于2个时，直接就是摇摆序列
        if (nums.length <= 2) {
            return nums.length;
        }

        //定义状态
        final int begin = 0;
        final int up = 1;
        final int down = 2;

        //每次比较当前值与下一个是正还是负，从而决定状态切换
        int state = begin;
        //摇摆序列的最大长度至少为1
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            switch (state) {
                case begin:
                    //当前后相等时，直接将前边舍掉，然后再去看下一个进行状态的确定
                    if (nums[i] > nums[i-1]) {
                        state = up;
                        maxLength++;
                    } else if (nums[i] < nums[i-1]){
                        state = down;
                        maxLength++;
                    }
                    break;
                case up:
                    /*当遇到连续的上升或下降，只有最开始的元素满足该状态，而我们实际要选取的元素时峰值或低谷，但是提前记录摇摆长度
                    并不会影响我们对元素的选取*/
                    if (nums[i] < nums[i -1]) {
                        state = down;
                        maxLength++;
                    }
                    break;
                case down:
                    if (nums[i] > nums[i -1]) {
                        state = up;
                        maxLength++;
                    }
                    break;
                default:throw new IllegalArgumentException("状态错误");
            }
        }
        return maxLength;
    }
}
