package com.xiaoxiang.hash_string;

import java.util.HashMap;
import java.util.Map;

/**
 * author:w_liangwei
 * date:2020/9/15
 * Description: 无重复字符最长子串 LeetCode 3
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        int substringLength = lengthOfLongestSubstring("pwwkew");
        System.out.println(substringLength);
    }

    /**
     * 采用双指针滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        //记录最大字符长度
        int maxLength = 0;
        //左指针
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //出现了重复字符,则保留刚出现的字符，去掉前边出现的字符。即left移动到重复位置的下一位
            //增加与left比较是为了应对如:abba这样的字符串，在第二个b加入时，发现重复出现b。left移动到第三个b的位置，按道理此时应该将map中此前构建的索引清空重新构建，这样才能判断后续子串是否出现重复。
            //但是为了应对这种情况下一个a加入时，即使判断重复也是和上一个子串构成的重复。和本次不构成重复，所以还是取当前的left。这样left才会只往前走而不会退回去
            if (map.containsKey(c)) {
                left = Math.max(map.get(c) + 1, left);
            }
            //更新该字符最新出现的位置
            map.put(c, i);
            //更新子串最大长度。每处理一个字符拿当前子串的长度与原来的最大长度比较，如果本次大则更新为最大长度
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }
}
