package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/11
 * Description: offer 20
 *
 * 合法数字的规则
 *     数字两边可以有空格，但是中间插空格不行；
 *     除了数字之外，合法字符还有'e'、'E'、'+'、'-'、'.'；
 *     'e'、'E'等价，用来划分底数与指数，只能出现一次，前面为科学计数法的底数，后面为指数；
 *     '+'、'-'只能作为正负号，但是不可以作为加减号，也就是只能出现在底数和指数的前面，不能出现在两个数字中间；
 *     '.'只能在底数上，不能在指数上，且只能出现一次，'.'两边任一边有数字均算一个完整的数字，但单独一个'.'不行。
 */
public class IsNumber {
    public static void main(String[] args) {
        String s = "12.3e12";
        String s1 = "123e";
        System.out.println(isNumber(s1));
    }

    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        //去掉首位空格
        s = s.trim();
        //这个标识符分表表示前边是否出现过数字，是否出现过点，是否出现过e
        boolean numFlag = false;
        boolean dotFlag = false;
        boolean eFlag = false;
        //遍历每一个字符进行检查
        for (int i = 0; i < s.length(); i++) {
            //当前字符是数字，则修改numFlag表示数字已经出现过了
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
                //当前字符是点，需要前边没出现过点，并且没出现过e才是合法数字。如符合要求则修改dotFlag表示点已经出现过了
                //因为出现过e的话代表当前检测的位置就是指数部分，而指数部分不允许小数出现，可以具体看一下类注释最开始的规则
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
                //当钱是字符e，需要前边没出现过e，并且出过了数字来作为底数部分
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                eFlag = true;
                //为了避免123e这种请求，出现e之后就标志为false.即有底数却没有指数的情况
                numFlag = false;
                //当前是正负号，只能出现在第一位或者紧接e后面。即正常数字的开头或科学计数的底数或指数的开头
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                //其他情况，都是非法的
            } else {
                return false;
            }
        }
        return numFlag;
    }
}
