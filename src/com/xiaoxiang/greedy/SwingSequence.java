package com.xiaoxiang.greedy;

/**
 * @Date 2020/9/1 6:52
 * @Auther 梁伟
 * @Description 摇摆序列
 */
public class SwingSequence {
    public static void main(String[] args) {

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
    public int wiggleMaxLength(int[] nums) {
        //定义状态
        final int begin = 0;
        final int up = 1;
        final int down = 2;

        //每次比较当前值与下一个是正还是负，从而决定状态切换
        int state = begin;
        switch (state) {
            case begin:

                break;
            case up:
                break;
            case down:
                break;
            default:throw new IllegalArgumentException("状态错误");
        }
        return 0;
    }
}
