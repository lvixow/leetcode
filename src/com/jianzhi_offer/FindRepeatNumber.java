package com.jianzhi_offer;

import java.util.HashSet;
import java.util.Set;

/**
 * author:w_liangwei
 * date:2020/12/16
 * Description: offer 03
 */
public class FindRepeatNumber {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};

        //解法1
//        Set<Integer> set = new HashSet<>();
//        for (int i : arr) {
//            if (!set.add(i)) {
//                System.out.println(i);
//            }
//        }


        //解法2
        //遍历每个数字将其放到自己的数组下标内，如数字2就放入索引2，
        //当放的过程中发现要放的位置的值和当前要放的值相等，说明出现了重复。即给每一个数字找到一个自己的家
        for (int i = 0; i < arr.length; i++) {
            //找自己的位置，需要回家
            if (arr[i] != i) {
                //回家了开门发现自己家里有一个同样的自己在那了，那说明重复了
                if (arr[i] == arr[arr[i]]) {
                    System.out.println(arr[i]);
                } else {
                    //家里没有人，那么自己就住下来
                    arr[arr[i]] = arr[i];
                }
            }
        }
    }
}
