package com.xiaoxiang.recursion_recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/6 17:42
 * @Auther 梁伟
 * @Description 括号生成 LeetCode 22
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        System.out.println(list);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs("", n, n, result);
        return result;
    }


    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/9/6 17:50
     * @Param [str, left, right, result] 一种括号结果，剩余左括号，剩余右括号，最终结果
     * @return void
     **/
    private static void dfs(String str, int left, int right, List<String> result) {
        //当左右括号都没有剩余时加入生成的结果
        if (left == 0 && right == 0) {
            result.add(str);
            return;
        }
        //左括号有剩余加左括号
        if (left > 0) {
            dfs(str + "(", left-1, right, result);
        }
        //只有右括号剩余大于左括号才能拼接右括号，即当前str中左括号多才能拼接右括号
        if (right > left) {
            dfs(str + ")", left, right-1, result);
        }
    }
}
