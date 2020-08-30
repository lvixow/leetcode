package com.xiaoxiang.stack_queue_heap;

import java.util.PriorityQueue;

/**
 * @Date 2020/8/31 6:08
 * @Auther 梁伟
 * @Description 寻找第K大的数
 */
public class FindKLargest {

    public static void main(String[] args) {
        int kthLargest = findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        System.out.println(kthLargest);
    }

    /**
     * @auther 梁伟
     * @Description 将元素遍历放入小顶堆，小顶堆总共维护K个元素，那么最后弹出堆顶就是第K大的元素
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * @Date 2020/8/31 6:08
     * @Param [nums, k]
     * @return int
     **/
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length == 0 || k < 0 || k > nums.length -1) {
            throw new RuntimeException("参数不合法");
        }
        //使用priorityQueue来提供小顶堆的实现
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();

        //遍历数组加入小顶堆
        for (int i = 0; i < nums.length; i++) {
            //当堆中元素小于k个直接添加
            if (priorityQueue.size() < k) {
                priorityQueue.add(nums[i]);
                continue;
            }
            //当堆中元素大于k个时，当前元素比堆顶大时，加入当前元素，并弹出堆顶
            if (nums[i] > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(nums[i]);
            }
        }
        return priorityQueue.peek();
    }
}
