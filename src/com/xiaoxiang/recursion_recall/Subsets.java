package com.xiaoxiang.recursion_recall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/9/4 6:40
 * @Auther 梁伟
 * @Description 集合的所有子集
 *
 *  三种解法
 * 1. 开始假设输出子集为空，每一步都向子集添加新的整数，并生成新的子集
 * 2. 通过 回溯 生成所有给定长度的子集。
 * 3. 位运算
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        List<List<Integer>> subsets = subsets(nums1);
        System.out.println(subsets);

        int[] nums2 = {1,2,2};
        List<List<Integer>> subsetsWithDup = subsetsWithDup(nums2);
        System.out.println(subsetsWithDup);


//        for (int i = 0; i < 8; i++) {
////            System.out.print("当前待匹配集合的二进制是:" + Integer.toBinaryString(i) + "\t\t");
//            for (int j = 0; j < nums1.length; j++) {
//                int yiwei = 1 << j;
////                System.out.print(Integer.toBinaryString(yiwei));
//                System.out.print(i & yiwei);
//                System.out.print("\t");
//            }
//            System.out.println();
//        }

    }


    /**
     * @auther 梁伟
     * @Description 以[1,2,3]为例，采用位图的方式实现。先去计算出所有子集，然后去匹配确定值
     * @Date 2020/9/4 7:41
     * @Param [nums]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> subsets(int[] nums) {
        //计算出所有子集的个数,每个元素都有放入和不放入两种情况，所以是2的n次方个子集。使用位运算可快速得出
        int allSubsets = 1 << nums.length;

        List<List<Integer>> result = new ArrayList<>();

        //遍历所有子集找到包含特定元素的子集
        for (int i = 0; i < allSubsets; i++) {
            //用于放子集元素
            List<Integer> item = new ArrayList<>();
            //倒序遍历检测该子集存在哪些元素，通过位运算确定元素的值
            for (int j = 0; j < nums.length; j++) {
                //i代表当前集合的一种子集，如011即{1,2}这个子集。二进制中的索引和数组中的索引顺序相反，如二进制中的第三个位置对应数组中的第一个位置
                //1 << j 构造一个类似于001、010、100这样和nums数组个数相关的数，用来检测是否包含指定元素
                /*如1 << 1 即为010。011（当前子集） & 010（用于检测子集的数）= 010。结果中1所在的位置即为集合中数组元素的位置。
                如nums[1,2,3]，当前子集011，使用010进行检测后，结果为010，确定了当前子集第二个位置是2。通过这样的检测对子集的所有位置进行确定，即可得出该子集的实际数字*/
                // 在10进制中左移一位或右移一位即除10或乘10，二进制中也类似，只是变换的是2的倍数。
                //如果该位置有当前检测的值，则&运算的二进制结果中因为包含1，所以结果肯定大于0
//                if ((i & (1 << j)) != 0) {
//                    item.add(nums[j]);
//                }
                if (((i >> j) & 1) ==1) {
                    item.add(nums[j]);
                }
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 找出所有元素不重复的子集
     * @param nums  可能包含重复元素
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 递归回溯寻找子集
     * 输入: [1,2,2]
     * 输出:[[2],[1],[1,2,2],[2,2],[1,2],[]]
     * @param nums  去重后的集合
     * @param result    结果集合
     * @param start     当前要加入的元素索引
     * @param temp  用于存放自己元素
     */
    private static void dfs(int[] nums, int start, List<Integer> temp, List<List<Integer>> result) {
        //每次进入先复制一份上一次递归的子集的结果，放入到总的结果集，然后再进行操作
        result.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            //比较当前元素是否上一个元素重复,重复则跳过。该数组已经被排序，所以重复元素会相邻
            // i > start保证了至少是开始遍历位置的后边一个元素或者更靠后的元素
            if (i > start && nums[i] == nums[i -1]) {
                continue;
            }
            //子集中加入当前元素
            temp.add(nums[i]);
            dfs(nums,i + 1, temp, result);
            temp.remove(temp.size() -1);
        }
    }
}
