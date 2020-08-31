package com.xiaoxiang.stack_queue_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date 2020/8/31 6:35
 * @Auther 梁伟
 * @Description 寻找中位数
 */
public class MedianFinder {

    //小顶堆
    PriorityQueue<Integer> minPriorityQueue = new PriorityQueue();

    //大顶堆
    PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    /**
     * @auther 梁伟
     * @Description 需要维护这两个状态
     * 1. 两个堆的元素差距不超过1，我们这里叫最大堆多1个
     * 2. 最大堆堆顶比最小堆堆顶小
     * @Date 2020/8/31 6:46
     * @Param [num]
     * @return void
     **/
    public void addNum(int num) {
        //当大顶堆的元素个数比小顶堆不超过1时，加入到大顶堆
        if (maxPriorityQueue.size() - minPriorityQueue.size() < 1) {
            //比较当前要添加元素和最小堆堆顶大小
            if (maxPriorityQueue.isEmpty() || num <= minPriorityQueue.peek()) {
                maxPriorityQueue.add(num);
            } else {
                //将小顶堆的堆顶元素转移到大顶堆，然后再添加当前元素到小顶堆
                int min = minPriorityQueue.poll();
                maxPriorityQueue.add(min);
                minPriorityQueue.add(num);
            }
        } else {
            //加入小顶堆
            if (minPriorityQueue.isEmpty() || num >= maxPriorityQueue.peek()) {
                minPriorityQueue.add(num);
            } else {
                //将大顶堆的堆顶元素转移到小顶堆，然后再添加当前元素到大顶堆
                Integer max = maxPriorityQueue.poll();
                minPriorityQueue.add(max);
                maxPriorityQueue.add(num);
            }
        }

    }

    /**
     * @auther 梁伟
     * @Description 使用最大堆和最小堆将数组分为两半，最大堆和最小堆的堆顶就是计算中位数时用到的值，
     * 具体根据元素是奇数个还是偶数个来决定
     * @Date 2020/8/31 6:40
     * @Param []
     * @return double
     **/
    public double findMedian() {
        int total = maxPriorityQueue.size() + minPriorityQueue.size();
        //根据元素总个数的奇数还是偶数决定怎么取中位数
        double median;
        if (total % 2 == 0) {
            median = (maxPriorityQueue.peek() + minPriorityQueue.peek()) / 2.0;
        } else {
            median = maxPriorityQueue.peek();
        }
        return median;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2);
        medianFinder.addNum(4);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());

        MedianFinder medianFinder1 = new MedianFinder();
        medianFinder1.addNum(2);
        medianFinder1.addNum(3);
        System.out.println(medianFinder1.findMedian());
    }
}
