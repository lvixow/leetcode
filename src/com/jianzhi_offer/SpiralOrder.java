package com.jianzhi_offer;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2021/1/15
 * Description: offer 29
 *
 * 设立上下左右4个边界，每遍历一条边的过后就将对应的边界缩小，一直执行上下左右遍历的循环，直到发生边界碰撞
 * 则说明走到了中心位置，全部元素完毕
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4},
                          {5, 6, 7, 8},
                          {9, 10, 11, 12}};

        int[] spiralOrder = spiralOrder(matrix);
        System.out.println(spiralOrder);
    }

    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        //定义上下左右四个边界，随着遍历的过程逐渐进行缩小
        int left = 0;
        int right = matrix[0].length -1;
        int top = 0;
        int bottom = matrix.length -1;
        int index = -1;

        while (true) {
            //从左到右
            for (int i = left; i <= right; i++) {
                print(matrix, top, i);
                res[++index] = matrix[top][i];
            }
            //缩小上边界
            top++;
            //如果缩小边界后发生了边界碰撞，则说明没有区域可以继续缩小了，也就是走到了最中心的位置且完成了遍历
            if (top > bottom) break;

            //从上到下
            for (int i = top; i <= bottom; i++) {
                print(matrix, i, right);
                res[++index] = matrix[i][right];
            }
            //缩小右边界
            right--;
            //如果缩小边界后发生了边界碰撞，则说明没有区域可以继续缩小了，也就是走到了最中心的位置且完成了遍历
            if (right < left) break;

            //从右到左
            for (int i = right; i >= left; i--) {
                print(matrix, bottom, i);
                res[++index] = matrix[bottom][i];
            }
            //缩小下边界
            bottom--;
            //如果缩小边界后发生了边界碰撞，则说明没有区域可以继续缩小了，也就是走到了最中心的位置且完成了遍历
            if (bottom < top) break;

            //从下到上
            for (int i = bottom; i >= top; i--) {
                print(matrix, i, left);
                res[++index] = matrix[i][left];
            }
            //缩小左边界
            left++;
            //如果缩小边界后发生了边界碰撞，则说明没有区域可以继续缩小了，也就是走到了最中心的位置且完成了遍历
            if (left > right) break;
        }
        return res;
    }

    public static void print(int[][] arr, int i, int j) {
        System.out.print(arr[i][j] + "\t");
    }
}
