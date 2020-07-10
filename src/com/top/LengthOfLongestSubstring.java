package com.top;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:w_liangwei
 * date:2020/7/10
 * Description: 采用双指针构建滑动窗口，窗口不一定是固定大小
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int start;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        List<Character> current = new ArrayList<>();

        //abcdadab
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            //出现重复字符
            if (current.contains(charAt)) {
                start = map.get(charAt) + 1;
                current.clear();
                char[] chars = s.substring(start, i + 1).toCharArray();
                for (char c : chars) {
                    current.add(c);
                }
            } else {
                current.add(charAt);
                if (maxLength < current.size()) {
                    maxLength = current.size();
                }
            }
            //不管是否出现重复字符都需要加入或覆盖字符位置
            map.put(charAt, i);
        }
        return maxLength;
    }
}
