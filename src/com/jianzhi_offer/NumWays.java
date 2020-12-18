package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/17
 * Description: offer10 -2
 *
 * 本题采用了动态规划的思想，也可以采用记忆化递归
 *
 * 由题意可知只能每次跳1个或2个台阶。一次跳1个台阶只有一种跳法；
 * 一次跳2个台阶有2种跳法，一种是是直接跳2个，另一种是先跳1个再跳1个（虽然跳了2下，但是符合题目要求）
 * 所以可以得到以下函数式
 *
 * 跳跃台阶数和跳法的函数关系
 * f(0) = 0                     n = 0       没有台阶时不能跳
 * f(1) = 1                     n = 1       有一个台阶时只有一种跳法
 * f(2) = 2                     n = 2       有2个台阶时有2种跳法
 * f(n) = f(n-1) + f(n-2)       n > 2       跳第n个台阶可以由第n-1或第n-2个台阶跳到第n个台阶，所以跳法就等于到达n-1阶和n-2阶跳法的总数
 */
public class NumWays {
    public static void main(String[] args) {
        System.out.println(numWays(0));
    }

    public static int numWays(int n) {
        int a = 1, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
