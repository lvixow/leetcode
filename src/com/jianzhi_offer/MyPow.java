package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/29
 * Description: offer 16
 *
 */
public class MyPow {
    public static void main(String[] args) {
        System.out.println(myPow(2, -2));
    }

    public static double myPow(double base, int exponent) {
        double result = 1;
        //解决指数为负数的问题，如2^-3 = 1 / (2^3)
        if (exponent < 0) {
            //递归方式调用
//            return myPow(1/base, -exponent);
            //为了解决指数过大溢出的问题，将指数减1，向外乘一个底数
            //Java 代码中 int32 变量 n∈[−2147483648,2147483647], 因此当 n=−2147483648时执行 n=-n 会出现越界错误
            return 1/base * myPow(1/base, -exponent - 1);
        }
        while (exponent > 0) {
            if (exponent % 2 == 0) {
                //指数为偶数，则把指数缩小为一半，底数变大成原来的平方
                //如3^6 = 3^2^3
                exponent = exponent / 2;
                base = base * base;
            } else {
                //如果指数为奇数，把指数减去1，使其变成一个偶数。并将减去的一个指数乘到结果中
                //如3^7 = 3^6 * 3
                exponent = exponent - 1;
                result = result * base;
            }
        }
        return result;
    }
}

