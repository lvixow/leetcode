package com.xiaoxiang.hash_string;

/**
 * author:w_liangwei
 * date:2020/9/14
 * Description: 最长回文子串 LeetCode 409
 *
 * 本质上是从已经提供的字符串中，使用这些字符来构造一个最长回文串。而最长回文串又分为奇数长度和偶数长度。偶数长度如：abccba。奇数长度如：abcba
 * 统计子串传中每个字符串出现的次数，出现偶数次数的都可以用来构造回文串。出现奇数个的只能有一个出现在回文串中，即回文串的中心对称轴处
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        int len = longestPalindrome("abccccdd");
        System.out.println(len);
    }


    public static int longestPalindrome(String s) {
        //按照ASCII码表从A-z之间总共的个数，大小写之间有6个其它特殊字符。所以总共58个。counter用来统计每个字母在字符串中出现的次数
        int[] counter = new int[58];
        //统计每个字符出现次数
        for (char c : s.toCharArray()) {
            counter[c - 'A'] = counter[c - 'A'] + 1;
        }

        //回文串长度
        int ans = 0;
        //遍历每个字符出现的次数
        for (int x : counter) {
            //因为奇数个数的回文串只能是有一种，我们先将所有偶数个数的字符用完，然后看字符是否有剩余，如果有剩余再加入一个组成奇数长度的回文串
            //x & 1 最后一位是1的就是1，是0的就是0.这样也就达到了奇数次数时减一的效果
            ans = ans + x - (x & 1);
        }
        //上一步出来的ans是偶数长度，如果还有字符没有使用，那么可以将该字符放入中间组成奇数长度的回文串。此时才是最大长度
        return ans < s.length() ? ans + 1 : ans;
    }
}
