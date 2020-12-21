package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2020/12/18
 * Description: offer 11
 *
 * 将数组分为两个有序数组，可以使用二分搜索，旋转点左侧的值一定大于它右侧的值
 */
public class MinArray {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};

        int left = 0;
        int right = arr.length -1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[right]) {
                //如果中间值大于最右边的值，说明旋转之后最小的数字肯定在mid的右边
                left = mid + 1;
            } else if (arr[mid] < arr[right]) {
                //如果中间值小于最右边的值，说明旋转之后最小的数字肯定在mid的前面
                //注意这里mid是不能减1的，比如[3，1，3]，我们这里只是证明了numbers[mid]比numbers[right]小，但有可能numbers[mid]是最小的，也就是那个旋转点，所以我们不能把它给排除掉
                right = mid;
            } else {
                //如这样的数组[3，1，3，3，3，3，3]和[3，3，3，3，3，1，3]虽然相等，但无法确定旋转点，但是直接将右指针直接左移一位并不会舍去旋转点的值，因为此时mid处和right处
                //的值是相等的，舍去了right的值，mid处的值却依然存在，所以旋转点的值依然是存在的
                right = right -1;
            }
        }
        System.out.println(arr[left]);
    }
}
