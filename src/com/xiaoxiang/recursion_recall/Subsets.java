package com.xiaoxiang.recursion_recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/4 6:40
 * @Auther 梁伟
 * @Description 集合的所有子集
 */
public class Subsets {

    public static void main(String[] args) {

    }

    /**
     * @auther 梁伟
     * @Description 以[1,2,3]为例
     * @Date 2020/9/4 7:41
     * @Param [nums]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> subsets(int[] nums) {
        //计算出所有子集的个数
        int allSubsets = 1 << nums.length;

        List<List<Integer>> result = new ArrayList<>();

        //遍历所有子集找到包含特定元素的子集
        for (int i = 0; i < allSubsets; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //i代表当前集合的一种子集，如011即{2,3}这个子集。1 << j表示当前包含当前元素的所有子集
                // 如1 << 1 即为010。当i & (1 << 1)即可从所有子集中快速找到包含该元素的所有子集。
                // 在10进制中左移一位或右移一位即除10或乘10，二进制中也类似，只是变换的是2的倍数。
                if ((i & (1 << j)) > 0) {

                }
            }
        }
        return null;
    }
}
