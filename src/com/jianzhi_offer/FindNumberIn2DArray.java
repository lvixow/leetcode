package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/16
 * Description: offer 04
 * 1.对每一行进行二分查找
 * 2.从右上角或左下角开始找时有这样的规律，如从右上角开始找，当该查找值比当前值小，则向左走。当查找值比当前位置的值
 * 大时就朝下走。这利用了行列都是单调增的规律
 *
 */
public class FindNumberIn2DArray {
    public static void main(String[] args) {
        int[][] arr = { {1,   4,  7, 11, 15},
                        {2,   5,  8, 12, 19},
                        {3,   6,  9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}};

        int target = 20;

        //从右上角利用规律开始找,使用一个数组表示行，列坐标。以左上角为原点
        int row = 0;
        int col = arr[0].length -1;
        //当前值没有找到且还没有到达最下角时，就继续寻找
        while (col >= 0 && row <= arr.length -1) {
            //比较target和当前位置值的大小，确定下一步去哪比较
            if (target > arr[row][col]) {
                //向下走
                row++;
            } else if (target < arr[row][col]) {
                //向左走
                col--;
            } else {
                System.out.println("值已找到：x：" + row + ", y：" + col);
                break;
            }
        }
    }
}
