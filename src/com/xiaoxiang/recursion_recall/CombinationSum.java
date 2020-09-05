package com.xiaoxiang.recursion_recall;

import java.util.*;

/**
 * @Date 2020/9/5 15:47
 * @Auther 梁伟
 * @Description 组合求和 LeetCode 40
 *
 *
 * 去重思想：
 * 这个避免重复当思想是在是太重要了。
 * 这个方法最重要的作用是，可以让同一层级，不出现相同的元素。即
 *                   1
 *                  / \
 *                 2   2  这种情况不会发生 但是却允许了不同层级之间的重复即：
 *                /     \
 *               5       5
 *                 例2
 *                   1
 *                  /
 *                 2      这种情况确是允许的
 *                /
 *               2
 *
 * 为何会有这种神奇的效果呢？
 * 首先 cur-1 == cur 是用于判定当前元素是否和之前元素相同的语句。这个语句就能砍掉例1。
 * 可是问题来了，如果把所有当前与之前一个元素相同的都砍掉，那么例二的情况也会消失。
 * 因为当第二个2出现的时候，他就和前一个2相同了。
 *
 * 那么如何保留例2呢？
 * 那么就用cur > begin 来避免这种情况，你发现例1中的两个2是处在同一个层级上的，
 * 例2的两个2是处在不同层级上的。
 * 在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
 * 必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
 * 第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
 *
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> combinationSum2 = combinationSum2(candidates, 8);
        System.out.println(combinationSum2);
    }

    /**
     * @auther 梁伟
     * @Description 子获得数组无重复子集的过程中进行剪枝，减少无效的递归
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:[[1, 7],[1, 2, 5],[2, 6],[1, 1, 6]]
     * @Date 2020/9/5 15:56
     * @Param [candidates, target]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        //为了去重，进行排序
        Arrays.sort(candidates);
        //使用栈来记录路径，该路径就是子集
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(0, candidates, len, result, path, target);
        return result;
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/9/5 16:03
     * @param candidates 候选数组
     * @param len        冗余变量
     * @param begin      从候选数组的 begin 位置开始搜索
     * @param target     表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param path       从根结点到叶子结点的路径
     * @param result
     * @return void
     **/
    private static void dfs(int begin, int[] candidates, int len, List<List<Integer>> result, Deque<Integer> path, int target) {
        //当target减到0时说明已经找到了符合要求的子集，加入结果集中返回
        if (target == 0) {
            //每次都需要拷贝一次path，用于下次使用
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            //当前值比target的剩余部分还要大。大剪枝
            if (target - candidates[i] < 0) {
                break;
            }

            //小剪枝。对同层的相同的元素去重，对不同层相同的元素保留。
            if (i > begin && candidates[i] == candidates[i-1]) {
                continue;
            }

            //开始回溯
            path.addLast(candidates[i]);

            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            //因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(i + 1, candidates, len, result, path, target - candidates[i]);
            path.removeLast();

            System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }
    }
}
