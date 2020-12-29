package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/29
 * Description: offer 15
 *
 * 通过将二进制数减1的操作，快速定位了最后一个是1的二进制位，从而不需要一个一个数
 * 处理该位完成后即将该位和后边所有位变为0，然后继续执行该操作
 */
public class HammingWeight {
    public static void main(String[] args) {

        int n = 0b00000010001001000;
        int res = 0;
        //当数字不为0，说明二进制数中还有1
        while (n != 0) {
            //处理当前数字最后一位1
            res++;
            System.out.println(Integer.toBinaryString(n));
            System.out.println(Integer.toBinaryString(n -1));
            /*
                为什么引入n-1
                    通过n-1的操作就直接找到了最右边的一个1，而无需位进行比较
                    通过将二进制数n减1得到，当前数字最后一位的1变0，这个1后边的所有位都变为1.
                    即执行减法像高位借位使高位变0，后边的所有位变1。如1001000 - 1 = 1000111
                减1后数字发生了什么变化
                    两个数字中不同的部分有2部分，一部分是最后一位1，减1后变0.
                    另一部分是减1后最后一位1后原本为0的位置全部变为1
                 与运算规则
                    与运算：两个1则为1，否则为0
                 为什么使用与运算
                    通过这个与操作恰好使得被处理完成的这个1和后边所有的位置都没有1，从而不会影响接下来继续向前处理
                    通过执行n 与n-1执行与运算，把不同的位都变为0，
             */
            n = n & n-1;
            System.out.println(Integer.toBinaryString(n));
            System.out.println("========================================");
        }
        System.out.println(res);
    }
}
