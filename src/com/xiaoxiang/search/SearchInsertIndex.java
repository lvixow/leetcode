package com.xiaoxiang.search;

/**
 * author:w_liangwei
 * date:2020/9/11
 * Description: 搜索插入位置 LeetCode 35
 *
 *
 * 二分查找分为两种
 * 1.在循环体内查找并返回，里边三个分支，循环控制条件left<=right，退出循环时left > right，不需要判断，找不到返回-1
 * 2.在循环体内缩小查找范围，里边两个分支，循环控制条件left<right，退出循环时left==right，需要对最终结果是否是目标值做判断
 *
 * 缩小查找范围写法有两种写法，下一轮搜索时一种是将mid分配给左边，另一种是将mid分配给右边。
 *
 * 当其中一个分支是left=mid这种需要小心，因为mid默认向下取整，当区间内只剩下2个元素时，mid计算后会取到left，
 * 而进入该分支后查找区间并没有缩小，进而可以再次进入循环，造成了死循环。这时候就需要将mid改为向上取整，
 * 即mid两个元素时mid会取到right的位置
 */
public class SearchInsertIndex {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int index = searchInsert(nums, 2);
        System.out.println(index);
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        //因为插入位置可能是数组最后一个元素的后边，所以right设置为数组长度
        int right = nums.length;

        //当left和right没有相遇则继续查找
        while (left < right) {
            int mid = (left + right) / 2;
            //当target大于nums[mid]则表明mid处的值不可能是查找值，而插入值的话肯定比mid处的大，则至少从mid+1开始
            if (nums[mid] < target) {
                //下一轮搜索区间是[mid+1,right]
                left = mid + 1;
            } else {
                //如果是查找值则正好和mid处相等直接返回
                //如果是插入值则表明target比mid处的值小，正好插入位置就是mid所在的位置
                //下一轮搜索区间[left,mid]
                right = mid;
            }
        }
        //最终left和right相等才会跳出上边的循环，所以此时返回哪个都可以
        return left;
    }
}
