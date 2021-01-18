package com.jianzhi_offer;

import java.util.Stack;

/**
 * author:w_liangwei
 * date:2021/1/18
 * Description: offer 31
 *
 * 先将元素入栈，入栈后比较栈顶元素是否是要弹出元素，是就一直弹出，直到不等或栈为空停止，然后再加入元素。最终栈如果全部弹空了则序列合法，否则不合法
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] poped = {4,5,3,2,1};

//        int[] pushed = {1,2,3,4,5};
//        int[] poped = {4,3,5,1,2};
        boolean stackSequences = validateStackSequences(pushed, poped);
        System.out.println(stackSequences);
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) return false;
        Stack<Integer> stack = new Stack<>();
        //用来标记数组中弹出到了哪个
        int popIndex = 0;
        for (int push : pushed) {
            stack.push(push);
            //加入后对栈中元素进行比较，如果相同就一直弹出，直到栈为空或栈顶元素与要弹出元素不同
            while (!stack.isEmpty() && popped[popIndex] == stack.peek()) {
                //弹出栈中元素，并将指针移动到下一个期待要弹出的元素
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}
