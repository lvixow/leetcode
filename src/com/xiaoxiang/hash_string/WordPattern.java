package com.xiaoxiang.hash_string;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * author:w_liangwei
 * date:2020/9/14
 * Description: LeetCode 290
 */
public class WordPattern {
    public static void main(String[] args) {
        String pattern = "abba";
        String words = "dog cat cat dog";
        boolean wordPattern = wordPattern(pattern, words);
        System.out.println(wordPattern);
    }

    public static boolean wordPattern(String pattern, String s) {
        String[] split = s.split(" ");

        //长度不匹配
        if (pattern.length() != split.length) {
            return false;
        }

        //使用反向映射，即建立字符串--索引位置这样的映射。利用map调用put方法的返回值
        Map hashMap = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            /*如果模式串中曾经放过该值则返回以前的索引，否则返回null。
            这样就建立了字符串---索引位置的映射，当初次加入时都是未null。
            当二次加入时返回的都是最开始的索引值，并更新为该字符串最新的索引位置，这样模式串和匹配串一直在保持同步更新。
            索引他们的返回值也一定相等，如果不等则说明该字符串最后一次出现的位置在两个串中是不一致的。则匹配失败*/

            /*//第一次加入为null，重复加入为模式串中该字符最新出现的位置
            Object patternPut = hashMap.put(pattern.charAt(i), i);
            //第一次加入为null，重复加入为该单词在字符串中最新出现的位置
            Object splitPut = hashMap.put(split[i], i);

            //这三句可以用Objects.equals来等效替换，但是不能使用变量接收后比较，只能直接传递参数
            if (patternPut == null && splitPut == null)
                continue;
            if (patternPut == null || splitPut == null)
                return false;
            if ((int) patternPut != (int) splitPut) {
                return false;
            }*/

            //如果出现位置不匹配，或者一个已经出现而另一个没有出现过，则匹配失败。使用Objects.equals方法可以避免对null值进行判断
            if (!Objects.equals(hashMap.put(pattern.charAt(i), i), hashMap.put(split[i], i))) {
                return false;
            }
        }
        return true;
    }
}
