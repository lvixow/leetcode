package com.xiaoxiang.stack_queue_heap;

import java.util.Stack;

/**
 * @Date 2020/8/30 17:01
 * @Auther 梁伟
 * @Description
 */
public class Solution {

    public static void main(String[] args) {
        int result = calculate("1+121 - (14+(5-6))");
        int result2 = calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(result2);
    }

    /**
     * @auther 梁伟
     * @Description 采用有限状态自动机完成计算
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     * @Date 2020/8/30 17:15
     * @Param [str] 输入的要计算的字符串
     * @return int
     **/
    public static int calculate(String str) {
        //设置状态常量
        final int STATE_BEGIN = 0;
        final int NUMBER_STATE = 1;
        final int OPERATION_STATE = 2;

        //创建数字栈和操作符栈
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();

        //遍历整个要计算的字符串进行处理
        int state = STATE_BEGIN;
        //用于将字符串转为数字
        int number = 0;
        //用于判断当前是否可用进行计算,1为可用计算，0为不能计算
        int compuateFlag = 0;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ' ') {
                continue;
            }
            switch (state) {
                case STATE_BEGIN:
                    if (charAt >= '0' && charAt <= '9') {
                        state = NUMBER_STATE;
                    } else {
                        state = OPERATION_STATE;
                    }
                    //回退，不然会造成跳过当前字符的处理
                    i--;
                    break;
                case NUMBER_STATE:
                    //将字符串转为数字
                    if (charAt >= '0' && charAt <= '9') {
                        number = number * 10 + charAt - '0';
                    } else {
                        //碰到了操作符，则将已经转换完成的数字入数字栈
                        numberStack.push(number);
                        //如果当前可以计算则进行计算
                        if (compuateFlag == 1) {
                            compute(numberStack, operationStack);
                        }
                        state = OPERATION_STATE;
                        number = 0;
                        i--;
                    }
                    break;
                case OPERATION_STATE:
                    //如果当前是运算符，则设置为可以计算状态
                    if (charAt == '+' || charAt == '-') {
                        compuateFlag = 1;
                        operationStack.push(charAt);
                        //如果当前为左括号，则不能进行计算
                    } else if (charAt == '(') {
                        state = NUMBER_STATE;
                        compuateFlag = 0;
                    } else if (charAt >= '0' && charAt <= '9') {
                        state = NUMBER_STATE;
                        i--;
                    } else if (charAt == ')') {
                        compute(numberStack, operationStack);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("状态不合法");
            }
        }
        //当最后一个数字后边没有操作符，那么就不会被压栈，所以需要最后压入一次
        if (number != 0) {
            numberStack.push(number);
            compute(numberStack, operationStack);
        }
        if (number == 0 && numberStack.isEmpty()) {
            return 0;
        }
        return numberStack.pop();
    }

    /**
     * @auther 梁伟
     * @Description 用于计算结果
     * @Date 2020/8/30 17:36
     * @Param [numberStack, operationStack]
     * @return int
     **/
    public static void compute(Stack<Integer> numberStack, Stack<Character> operationStack) {
        if (numberStack.size() < 2) {
            return;
        }

        Integer num1 = numberStack.pop();
        Integer num2 = numberStack.pop();
        Character operation = operationStack.pop();

        int result = 0;
        if (operation == '+') {
            result = num1 + num2;
        } else if (operation == '-') {
            result = num2 - num1;
        }
        numberStack.push(result);
    }
}
