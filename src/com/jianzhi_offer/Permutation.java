package com.jianzhi_offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * author:w_liangwei
 * date:2021/1/22
 * Description: offer 38
 *
 * 全排列的思路
 * 1. 分别对每个位置放置不同的字符，如abc字符串，第一个字符的位置可以放置a，b，c三种情况
 * 2. 当确定了第一个位置之后，实际上剩余情况就是去除第一个位置后续的全排列。按照此思路递归，直到放置的位置是字符串的
 * 最后一个位置，此时也只剩下了一个字母没有放，那么该位置就放置该字母
 * 3. 递归的过程是对数组做全排列的过程，只是全排列的区间越来越小，直到指针碰撞退出
 * 4. 对于字符去重问题，当前位置后边如果多次出现同一个字母，如多个a，那么我们只需要对当前位置放一次a即可，其它的a
 * 再次放过来的时候其实就已经重复了，所以需要去重
 * 5.往当前位置放字符采用的是后续位置与当前位置交换的思想，由于要与后续每一个位置交换所以有for循环，每次交换则确定
 * 了当前位置是一种字母的情况，然后再对下一个区间进行全排列
 * 6. 当当前位置对一种字母的所有情况枚举完成后，退回到了本层递归，需要恢复到当前位置最初的情况，然后开始进行下一次交换
 * 如abc,第一次交换bac，第二次交换不进行回溯cab,进行回溯cba，这就是区别
 */
public class Permutation {

    public static void main(String[] args) {
        String s = "abc";
        String[] permutation = permutation(s);
        System.out.println(Arrays.toString(permutation));
    }

    public static String[] permutation(String s) {
        List<String> res = new LinkedList<>();
        dfs(s.toCharArray(), 0, s.length() -1, res);
        String[] ans = new String[res.size()];
        int i = 0;
        for (String re : res) {
            ans[i++] = re;
        }
        return ans;
    }

    /**
     * 通过left和right确定一个全排列的区间
     * @param arr
     * @param left
     * @param right
     */
    public static void dfs(char[] arr, int left, int right, List<String> res) {
        //当进行全排列的区间中只有一个元素时，则整个字符串完成一次全排列
        if (left == right) {
            res.add(String.valueOf(arr));
            return;
        }
        Set<Character> set = new HashSet<>();
        //left所在位置是当前位置领导位置，也就是该位置后的所有其他元素要与该位置交换，从而完成该位置元素的选取，所以使用一个循环与后边所有字符交换
        for (int i = left; i <= right; i++) {
            //当前被替换位置是该字母的情况已经被换过了，那么就不需要继续了。如第一位是a的情况有一个字母已经枚举过了，
            //那么下边再碰到重复的该字母也不应该再去枚举了,那么会生成多次该字母的全排列，实际则有了重复元素
            if (set.contains(arr[i])) continue;
            set.add(arr[i]);
            //与后边的字符交换
            swap(arr, i, left);
            //这个位置已经固定了一个字符，继续对下一个区间进行全排列
            dfs(arr, left + 1, right, res);
            //恢复到原来的状态，不然该位置尝试下一个字符时，其实交换得到的就与预想的不一致，
            //如原来是abc，下一次是bac，那么再次就应该是cba,如果不执行再次换回来的操作那么就是cab了，这就是错误的结果
            swap(arr, i, left);
        }
    }

    private static void swap(char[] c, int i, int x) {
        char temp = c[i];
        c[i] = c[x];
        c[x] = temp;
    }
}
