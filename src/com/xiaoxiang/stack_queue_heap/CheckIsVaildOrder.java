package com.xiaoxiang.stack_queue_heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Date 2020/8/30 15:48
 * @Auther 梁伟
 * @Description 合法的出栈序列
 */
public class CheckIsVaildOrder {
    public static void main(String[] args) {
        Queue queue = new ConcurrentLinkedQueue();
        queue.add(3);
        queue.add(2);
        queue.add(5);
        queue.add(4);
        queue.add(1);

        System.out.println(checkIsVaildOrder(queue));

        queue.clear();
        queue.add(3);
        queue.add(1);
        queue.add(2);
        queue.add(4);
        queue.add(5);
        System.out.println(checkIsVaildOrder(queue));
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/8/30 15:58
     * @Param [order]
     * @return boolean
     **/
    public static boolean checkIsVaildOrder(Queue<Integer> order) {
        if (order.isEmpty()) {
            return true;
        }
        //对要检测的队列元素进行排序，然后入栈
        Queue<Integer> temp = new PriorityQueue(order);
        //使用栈来检测出栈顺序是否合法
        Stack<Integer> stack = new Stack<>();
        int orderSize = order.size();
        for (int i = 0; i < orderSize; i++) {
            //将队列元素依次加入到栈中
            stack.push(temp.poll());
            //如果元素相同，则当前序列当前位置合法，同时出栈，出队列即可。直到元素不同时则出栈停止
            while (!stack.isEmpty() && stack.peek() == order.peek()) {
                stack.pop();
                order.poll();
            }
        }
        //如果栈弹空则序列合法，否则不合法
        return stack.isEmpty();
    }
}
