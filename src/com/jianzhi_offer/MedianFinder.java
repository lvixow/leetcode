package com.jianzhi_offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * author:w_liangwei
 * date:2021/2/3
 * Description: offer 41
 *
 * 用大顶堆 + 小顶堆方法，可以看作大顶堆是普通班，小顶堆是实验班。数量上时刻保持 大顶堆 - 小顶堆<=1（两堆相等或者大顶比小顶多一个）。
 * 新学生先入普通班（大顶堆），此时可能会失去平衡了，于是取大顶堆的第一个（班里最好的学生）加入实验班（小顶堆），判断若数量过多（不是等于或多一个），
 * 取第一个（实验班里最差的学生）到普通班（大顶堆）里。 取中位数的时候，若两堆数量相等，则各取堆顶取平均，若小顶比大顶多一，则多的那一个就是中位数。
 */
public class MedianFinder {

    /**
     * 保存中位数之前的数，类比成普通班
     */
    private PriorityQueue<Integer> maxHeap;

    /**
     * 保存中位数之后的数，类比成尖子班
     */
    private PriorityQueue<Integer> minHeap;


    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        this.minHeap = new PriorityQueue<>(Comparator.naturalOrder());
    }

    public void addNum(int num) {
        //维护大顶堆与小顶堆想差不超过一个元素，且元素优先放入大顶堆
        //新来的学生先加入普通班，普通班出来一个人进入尖子班
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        //当最小堆比最大堆元素多时，则将元素放入最大堆调整平衡，保证最多只相差一个，且最大堆元素个数在每时每刻都 大于等于 最小堆元素个数
        //始终保证普通班的人数大于等于尖子班
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        //当相等时，2n就是偶数个，那么取两个堆顶的和除以2
        if (maxHeap.size() == minHeap.size()) {
            //此处只能乘以0.5，如果是除以2会被取整导致结果错误
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
        //两个堆不等，说明前边的大顶堆比小顶堆多1，那么此时大顶堆的堆顶就是中位数，直接弹出即可
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        System.out.println(obj.findMedian());
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}
