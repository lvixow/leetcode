package com.jianzhi_offer;


/**
 * author:w_liangwei
 * date:2020/12/17
 * Description: offer 10-1
 * 1. 记忆化递归
 * 2. 正向动态规划
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(fib(5));
    }

    public static int fib(int n) {
        int a = 0, b = 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        //最后一次循环，b求和一次导致得到的值是n + 1, 而a此时恰好在n处。以上说的n都是从0开始编号
        return a;
    }
}
