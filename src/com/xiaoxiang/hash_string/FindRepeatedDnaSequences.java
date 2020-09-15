package com.xiaoxiang.hash_string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author:w_liangwei
 * date:2020/9/15
 * Description: 查找重复的DNA序列 LeetCode 187
 *
 * 1.遍历枚举所有长度为10的序列，放入set集合，后续如果出现重复则符合要求
 * 2.采用位运算对空间进行优化，由于每次子串变化只会舍弃前一位多了后一位，中间其它的都没有变，那么就可以利用这些信息进行优化
 */
public class FindRepeatedDnaSequences {
    public static void main(String[] args) {
        List<String> dnaSequences = findRepeatedDnaSequences("AAAAAAAAAAAA");
        System.out.println(dnaSequences);
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        //用于比较是否出现过重复，使用set集合不会超时可能和底层使用哈希结构实现有关，如果使用list当LeetCode传一个超大字符串时造成超时
        //使用contains方法查询元素是否存在HashSet要比ArrayList快的多。ArrayList的contains拿出数组中的所有元素一一比较，而hashSet底层使用hashmap的哈希定位到链表然后取值
        Set<String> temp = new HashSet<>();
        //用于存放最终结果，由于可能会出现重复结果，所以需要用set去重
        Set<String> res = new HashSet<>();
        //遍历所有长度为10的子字符串
        for (int i = 0; i <= len - 10; i++) {
            String substring = s.substring(i, i + 10);
            if (temp.contains(substring)) {
                res.add(substring);
            } else {
                //出现次数大于1次，符合要求，加入结果集
                temp.add(substring);
            }
        }
        return new ArrayList<>(res);
    }
}
