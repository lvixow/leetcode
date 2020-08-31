package com.xiaoxiang.greedy;

import java.util.Arrays;

/**
 * @Date 2020/9/1 5:52
 * @Auther 梁伟
 * @Description 分糖果
 */
public class SharedCandy {
    public static void main(String[] args) {
        int count1 = findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1});
        System.out.println(count1);

        int count2 = findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3});
        System.out.println(count2);

        int count3 = findContentChildren(new int[]{2, 5, 9, 9, 10, 5}, new int[]{1, 3, 6, 8, 20});
        System.out.println(count3);
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/9/1 5:59
     * @Param [children, candy] 每个孩子的胃口，糖果数量数组
     * @return int
     **/
    public static int findContentChildren(int[] children, int[] candy) {
        if (children.length == 0 || candy.length == 0) {
            return 0;
        }
        Arrays.sort(children);
        Arrays.sort(candy);

        //双指针遍历孩子和糖果
        int childIndex = 0;
        int candyIndex = 0;
        //当糖果和孩子都有就一直寻找，直到孩子都被满足或没有糖果可以满足
        while (childIndex < children.length && candyIndex < candy.length) {
            //使用糖果尝试去逐个匹配孩子
            if (candy[candyIndex] >= children[childIndex]) {
                //可以满足则更新可以满足的数量值
                childIndex++;
            }
            //当前糖果数量要么满足了孩子，如果连最小要求都不满足则抛弃该糖果。所以肯定要更新索引
            candyIndex++;
        }
        return childIndex;
    }
}
