package com.jianzhi_offer;

/**
 * author:w_liangwei
 * date:2021/1/4
 * Description: offer 19
 * 使用递归所有可能的方式来解决，是一种好的方法，但相对来说可能最好理解的方法。面试的话主要考的是dp的思想，可以使用dp来解
 * 在实际应用中最正宗的解法其实是有限状态自动机，但是涉及的知识可能比较多，具体可搜索编译原理课程，其内包含这部分内容
 */
public class IsMatch {
    public static void main(String[] args) {
        String s = "ab";
        String p = "a*";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        //在p串空串的情况下，s串为空则匹配，不为空则不匹配
        if (p.isEmpty())return s.isEmpty();
        //判断s和p的首字符是否匹配，注意要先判断s不为空
        //当s和p比较的字符是相同或p串的字符是 . ，那么说明当前可以匹配成功
        boolean headMatched=!s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        //当p串的下一个带比较字符是*时，需要进行复杂匹配
        if (p.length()>=2&&p.charAt(1)=='*'){//如果p的第一个元素的下一个元素是*
            //则分别对两种情况进行判断
            //一种是p串中的*前边的字符，也就是首字符，和s串匹配不上，那么说明*前边的那个字符出现了0次，下一次比较再用s串当前的字符和p串跳过到*号后的那个字符进行比较
            //例如s：bc、p：a*bc，我们就保持s不变，减掉p的"a*"，调用isMatch(s:bc,p:bc)。
            //另一种p串中*号前边的字符，也就是首字符和s串匹配上了，那么该字符已经出现了1次，需继续往后比较s串看有没有还出现更多次的该字符，所以s串应跳到下一个字符继续和p串的（首字符和*）进行比较
            //例如s：aabb、p：a*bb，就保持p不变，减掉s的首元素，调用isMatch(s:abb,p:a*bb)。
            return isMatch(s,p.substring(2))||
                    (headMatched&&isMatch(s.substring(1),p));
        }else if (headMatched){//否则，如果s和p的首字符相等
            return isMatch(s.substring(1),p.substring(1));
        }else {
            return false;
        }
    }
}
