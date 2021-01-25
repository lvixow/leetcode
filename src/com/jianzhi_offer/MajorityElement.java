package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/22
 * Description: offer 39
 *
 * 解法1：将所有数据排序后，由于该数字的位数大于数组长度的一半，所以肯定在数组中间
 * 解法2：摩尔投票，遍历数组，与前一个数字相同则增加统计数量，不同则减少。最后数组中剩下的肯定就是大于数组长度一半的数字
 *。此方法不是一个找众数的方法，只能找到占数组中半数以上的数
 *
 * 详情过程请参考知乎：https://www.zhihu.com/question/49973163    该链接介绍了摩尔投票的具体流程
 *
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        int majorityElement = majorityElement(arr);
        System.out.println(majorityElement);
    }

    public static int majorityElement(int[] nums) {
        //定义两个变量，一个记录对冲数组长度，一个记录对冲数据值。对冲数组中只会一个相同的值
        int count = 0;
        int num = 0;
        for (int i : nums) {
            //当前对冲数组为空，则添加该数准备用来对冲
            if (count == 0) {
                count = 1;
                num = i;
            } else {
                //被遍历数与对冲数组中的数相同则无法对冲，将其添加到对冲数组
                if (i == num) {
                    count++;
                } else {
                    //不相同则可以对冲，对冲过后对冲数组长度减1
                    count--;
                }
            }
        }
        return num;
    }
}
