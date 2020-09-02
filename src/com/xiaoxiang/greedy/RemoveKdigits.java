package com.xiaoxiang.greedy;

import java.util.Stack;

/**
 * @Date 2020/9/2 6:52
 * @Auther 梁伟
 * @Description 从一个数字中移除K位后，生成的数字最小
 */
public class RemoveKdigits {
    public static void main(String[] args) {
        String num1 = "1432219";
        String num2 = "10200";
        String num3 = "10";
        String num4 = "123453";
        String removeKdigits = removeKdigits(num4, 3);
        System.out.println(removeKdigits);
    }

    /**
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (k == 0) {
            return num;
        }
        if (num.length() == k) {
            return "0";
        }
        if (k > num.length()) {
            throw new IllegalArgumentException("移除位数超出数字长度");
        }

        //用于存放字符串类型的数字
        Stack<Character> numStack = new Stack<>();
        //遍历每个数字，当正在遍历的值（后一个数字）比栈顶元素（前一个数字）小，则删除前一个数字，即将栈顶数字出栈。这样可以保证高位最小，即数字整体最小
        for (int i = 0; i < num.length(); i++) {
            char charAt = num.charAt(i);
            //当栈不空，前一个比后一个大，移除的位数未到达则移除。当栈中一直有元素一直可以弹，即一次移除多个数字。如1564，k=2，此时4可以连续将5和6都移除
            while (!numStack.isEmpty() && numStack.peek() > charAt && k > 0) {
                numStack.pop();
                k--;
            }
            //在栈为空时不能加入0，这样保证移除位数后的数字不会以0为起始位
            if (!numStack.isEmpty() || charAt != '0') {
                numStack.push(charAt);
            }
        }

        //移除位数还未完成，此时还需要移除剩余的数字。如12345每个数字单调递增的的或12343部分单调递增的，通过上边的处理一个都移除不了或只能部分移除，那么就还需要下边的处理
        while (!numStack.isEmpty() && k > 0) {
            numStack.pop();
            k--;
        }

        //将栈中元素转为字符串
        StringBuilder sb = new StringBuilder();
        while (!numStack.isEmpty()) {
            sb.append(numStack.pop());
        }
        return sb.reverse().toString();
    }
}
