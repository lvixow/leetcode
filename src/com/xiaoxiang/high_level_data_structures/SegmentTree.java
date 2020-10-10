package com.xiaoxiang.high_level_data_structures;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2020/10/10
 * Description: 线段树
 *
 * 使用数组构造树左右节点的关系
 * left_node = 2 * node +1;
 * right_node = 2 * node +2;
 *
 * segmentTree[node] = nums数组中索引start到end的和
 *
 */
public class SegmentTree {

    //使用数组表示表示线段树
    int[] segmentTree;

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree obj = new SegmentTree(nums.length);
        obj.buildTree(nums, 0, 0, nums.length -1);
        System.out.println(Arrays.toString(obj.segmentTree));
//        obj.update(nums, 0,4, 10, 0, nums.length -1);
        System.out.println(Arrays.toString(obj.segmentTree));
        int sumRange = obj.sumRange(0, 0, nums.length - 1, 2, 4);
        System.out.println(sumRange);
    }

    public SegmentTree(int length) {
        //数组所需最大容量等于nums最后一个数计算右节点时得到的索引，也就是rightNode = 2 * node + 2  =  2 * (length -1) + 3 = 2 * length + 1。由于数组初始化表示的是容量索引还需要加一
        this.segmentTree = new int[2 * length + 1];
    }

    /**
     * 从segmentTree的顶部开始，计算左右子节点在segmentTree中的索引，判断index是在左子树还是右子树，逐渐缩小范围，最终使用左右子节点的索引来更新segmentTree数组
     * @param nums  原数组
     * @param node  待处理节点在segmentTree中的索引
     * @param index 要更新的数字在原数组中的索引
     * @param val   要更新成的值
     * @param start 查找index在segmentTree数组中所处的索引时使用，在segmentTree中的起始索引
     * @param end   查找index在segmentTree数组中所处的索引时使用，在segmentTree中的结束索引
     */
    public void update(int[] nums, int node, int index, int val, int start, int end) {
        if (start == end) {
            segmentTree[node] = val;
            nums[index] = val;
        } else {
            //计算mid确定index在segmentTree的左子树还是右子树
            int mid = (start + end) / 2;
            //计算index节点对应的左右节点
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            //index在segmentTree的左子树上
            if (index <= mid) {
                update(nums, leftNode, index, val, start, mid);
            } else {
                //index在segmentTree的右子树上
                update(nums, rightNode, index, val, mid + 1, end);
            }
            //递归退出后用下一层修改完成的值计算上一层的值
            segmentTree[node] = segmentTree[leftNode] + segmentTree[rightNode];
        }
    }

    /**
     * 计算数组索引区间内的和
     * @param start
     * @param end
     * @param L 求和区间的起始索引
     * @param R 求和区间的结束索引
     * @return
     */
    public int sumRange(int node, int start, int end, int L, int R) {
        //不在计算范围
        if (start > R || end < L) {
            return 0;
        } else if (start >= L && end <= R) {
            //当start到end的区间在计算范围内部时，直接返回结果，不用继续往下递归重新计算。区间如下所示：L    start      end       R
            return segmentTree[node];
        }else if (start == end) {
            //递归到达了子节点直接返回结果
            return segmentTree[node];
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            int sumLeft = sumRange(leftNode, start, mid, L, R);
            int sumRight = sumRange(rightNode, mid + 1, end, L, R);
            return sumLeft + sumRight;
        }
    }

    /**
     *
     * @param nums  要计算的数组，也就是原数组
     * @param node  要构建节点在线段数组中的索引，也就是segmentTree中的索引
     * @param start 构建的起始位置
     * @param end   构建的结束位置
     * @return
     */
    public void buildTree(int[] nums, int node, int start, int end) {
        //说明到达最下层的子节点
        if (start == end) {
            segmentTree[node] = nums[start];
        } else {
            //分成左右两部分进行构建，递归缩小构建范围
            int mid = (start + end) / 2;
            //计算当前node节点的左右节点在segmentTree中的位置
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            //递归构建左右节点
            buildTree(nums, leftNode, start, mid);
            buildTree(nums, rightNode, mid + 1, end);
            //递归已经将左右节点的和求出来了，此时可以计算node节点的和
            segmentTree[node] = segmentTree[leftNode] + segmentTree[rightNode];
        }
    }
}
