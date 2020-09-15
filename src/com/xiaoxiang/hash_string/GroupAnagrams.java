package com.xiaoxiang.hash_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:w_liangwei
 * date:2020/9/15
 * Description: LeetCode 49
 *
 *
 * 算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式
 *
 * 1. 将每个字符串中的字符排序后，然后加入map，key为排序后的字符串，value为字符串集合
 * 2. 使用26个质数与字母一一对应，根据数学性质，当质数的积相同时则存在的字符及其个数也是相同的
 *
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(lists);
    }

    /**
     * 一个数如果不是质数，那么就可以被分解为两个质数的乘积。即实现了只有组合相同才能得到同样的质数积，和字符顺序无关
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        //枚举26个质数与26个字母一一对应
        int[] peime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

        //质数积---单词集合
        Map<Long, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            //由于是乘法，初始化为1不会影响结果。这里的key被累乘，可能造成整型溢出
            long key = 1;
            //遍历当前字符串的每一个字符，计算其质数积
            char[] chars = strs[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                //获得当前字符对应的质数，并与上一位的处理结果继续相乘
                key = key * peime[chars[j] - 'a'];
            }
            //当前质数积不存在，证明该组合字符串从未出现过
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(key, list);
            } else {
                //出现过则添加当前字符串
                map.get(key).add(strs[i]);
            }
        }
        return new ArrayList<>(map.values());




        //第二种方式，对每个字符串内的字符进行排序后，相同即为一组的
       /* Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            //对字符串内字符排序
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            //根据key放入不同的组
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(key, list);
            } else {
                map.get(key).add(strs[i]);
            }
        }
        return new ArrayList<>(map.values());*/
    }
}
