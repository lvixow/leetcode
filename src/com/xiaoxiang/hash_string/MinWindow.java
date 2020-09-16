package com.xiaoxiang.hash_string;

import java.util.Arrays;

/**
 * @Date 2020/9/16 6:14
 * @Auther 梁伟
 * @Description 最小覆盖子串 LeetCode 76
 */
public class MinWindow {
    public static void main(String[] args) {
        String minWindow = minWindow("ADOBECODEBANC", "ABCA");
        System.out.println(minWindow);
    }

    public static String minWindow(String s, String t) {
        //左右指针
        int left = 0;
        int right = 0;
        //当前待匹配个数,当为0时则t中的全部字符已经匹配
        int count = t.length();
        //用于保存子串上一次左右节点的值，默认为s的长度
        int res_left = 0;
        int res_right = s.length() -1;


        //用于统计t中每个字符出现的次数
        int[] map = new int[128];
        //初始化t中每个字符出现的次数
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        //移动右指针
        while (right < s.length()) {
            right++;
            //移动后新出现的字符是否在s中有，有则对对应字符的次数减一，并将总次数也减一
            if (map[s.charAt(right)] != 0) {
                map[s.charAt(right)]--;
                count--;
            }
            //当前子串已经包含了t中所有字符，每个字符的出现次数也符合。移动左指针，缩小子串
            while (count == 0) {
                //看当前子串是否是最小的子串，从而决定是否更新子串
                if (res_right - res_left > right - left) {
                    res_left = left;
                    res_right = right;
                }
                //左移将计数器重新恢复，可能恢复包含了t以外的其它元素
                map[s.charAt(left)]++;
                //移动左指针
                left++;

            }
        }











        return "";
    }
}
