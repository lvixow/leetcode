package com.jianzhi_offer;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2021/1/4
 * Description: offer 17
 *
 * 主要难点
 *          解决大数的打印，大数只能通过字符串来表示
 *          对生成的数字前边为0格式化的问题
 */
public class PrintNumbers {

    //构造char数组，用于方便构造字符串来输出数字
    static char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static char[] result;

    public static void main(String[] args) {
        int n = 1;
        result = new char[n];
        StringBuilder sb = new StringBuilder();
        //每次单独构建不同的位数，如总共要打3位数，那么就是1-999，我们分别先打一位数，然后打二位数，最后打三位数，那么就相同于完成了任务
        for (int i = 0; i < n; i++) {
            dfs(0, i, sb);
        }
        sb.deleteCharAt(sb.length() -1);
        System.out.println(sb.toString());
        System.out.println(sb.toString().split(",").length);

        //官方题目要求，可通过。但实际面试不会问这样的
//        int end = (int) (Math.pow(10, n));
//        int[] res = new int[end -1];
//        for (int i = 1; i < end; i++) {
//            res[i -1] = i;
//        }
//        Arrays.toString(res);
    }



    /**
     *
     * @param index        当前处理的位置索引
     * @param lastIndex    最后一个要处理的索引
     * @return
     */
    public static void dfs(int index, int lastIndex, StringBuilder sb) {
        //索引越界说明一个符合要求的数字已经处理完成，保存到字符串中
        if (index > lastIndex) {
            sb.append(String.valueOf(result)).append(",");
            return;
        }

        //当要填充索引为0的位置时，那么就不要填0。所以选择填充数组的索引从1开始
        int start;
        if (index == 0) {
            start = 1;
        } else {
            start = 0;
        }

        //取每一个数字来填充当前索引位置，然后继续递归填充下一个位置
        for (int i = start; i < 10; i++) {
            result[index] = nums[i];
            dfs(index + 1, lastIndex, sb);
        }
    }
}
