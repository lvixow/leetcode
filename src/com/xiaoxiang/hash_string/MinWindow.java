package com.xiaoxiang.hash_string;


/**
 * @Date 2020/9/16 6:14
 * @Auther 梁伟
 * @Description 最小覆盖子串 LeetCode 76
 */
public class MinWindow {
    public static void main(String[] args) {
        String minWindow = minWindow("ADOBECODEBANC", "ABC");
        System.out.println(minWindow);
    }

    /**
     * @auther 梁伟
     * @Description 采用双指针滑动窗口，先右移右指针指针获得子串，然后右移左指针获得最小子串直到走完整个字符串
     *
     * 对次数的统计采用了扣减要匹配的字符次数和待扣减总次数，来减少每次扣减完当前字符次数去
     * 遍历每个待匹配字符的计数器来查看子串是否符合要去的步骤，而变成了只需要比较count待匹配总次数
     * 是否为0就可以了
     * @Date 2020/9/17 7:40
     * @Param [s, t] 源字符串，要匹配的子串
     * @return java.lang.String
     **/
    public static String minWindow(String s, String t) {
        //左右指针
        int left = 0;
        int right = 0;
        //当前待匹配个数,当为0时则t中的全部字符已经匹配
        int count = t.length();
        //用于保存子串上一次左右节点的值
        int res_left = 0;
        //子串的右指针初始化为-1，是因为我们在找不到符合要去的子串的时候要返回空字符串
        //而这个右指针为-1，恰好在返回时substring什么都没截取到就是一个空字符串
        int res_right = -1;
        //初始化最小子串长度为最大值，用于决定什么时候更新保存最终结果的左右指针
        int substringLen = Integer.MAX_VALUE;


        //用于统计t中每个字符出现的次数，长度是根据ASCII码表确定的
        int[] map = new int[128];
        //初始化t中每个字符出现的次数,形成一个只有t字符串中字符大于0，其它位置都是0
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        //移动左右指针获得子串
        while (right < s.length()) {
            //新出现字符对应计数器减一
            map[s.charAt(right)]--;
            //如果减完了是>=0，说明其原来这个字符的计数器被加过，而加只能是发生在初始化t字符串计数器的时候
            //所以新出现字符就是t中的字符，并把要匹配的长度减一
            //而这时候其它未匹配字符的计数器，在右指针左侧的是负数，在右指针右侧的是0
            if (map[s.charAt(right)] >= 0) {
                count--;
            }
            //当前子串已经包含了t中所有字符，每个字符的出现次数也符合。移动左指针，缩小子串
            while (count == 0) {
                //看当前子串是否是最小的子串，从而决定是否更新子串
                //当前窗口大小
                int tempLen = right - left + 1;
                if (substringLen > tempLen) {
                    res_left = left;
                    res_right = right;
                    substringLen = tempLen;
                }
                //因为即将要左指针右移，则当前左指针位置的字符计数器将重新加一恢复
                //即右指针左侧字符所有计数器随之左指针的右移而计数器调升
                char departingCharacter = s.charAt(left);
                map[departingCharacter]++;
                //当左指针离开计数器恢复，而不是t中字符的计数器是负数，恢复也只能是恢复到0。只有t中字符的计数器才是大于0的
                //这和初始化t中字符计数器有关，以及我们所使用的是计数器次数扣减策略
                //此时说明左指针即将离开的字符就是t中的字符
                if (map[departingCharacter] > 0) {
                    //对要统计t中字符的总次数加一，用于移动右指针
                    count++;
                }
                //完成对左指针的移动
                left++;
            }
            //先对右指针所处位置的字符做操作，最后右指针右移，扩大窗口。先移动会跳过s的首个字符
            right++;
        }
        return s.substring(res_left, res_right + 1);
    }
}
