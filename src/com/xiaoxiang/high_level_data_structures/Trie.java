package com.xiaoxiang.high_level_data_structures;

import java.util.*;

/**
 * @Date 2020/10/9 5:40
 * @Auther 梁伟
 * @Description 字典树
 */
public class Trie {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        TrieNode n1 = new TrieNode();
        TrieNode n2 = new TrieNode();
        TrieNode n3 = new TrieNode();

        root.child['a'-'a'] = n1;
        root.child['b'-'a'] = n2;
        root.child['e'-'a'] = n3;
        n2.isEnd = true;


        TrieNode n4 = new TrieNode();
        TrieNode n5 = new TrieNode();
        TrieNode n6 = new TrieNode();
        n1.child['b'-'a'] = n4;
        n2.child['c'-'a'] = n5;
        n3.child['f'-'a'] = n6;


        TrieNode n7 = new TrieNode();
        TrieNode n8 = new TrieNode();
        TrieNode n9 = new TrieNode();
        TrieNode n10 = new TrieNode();
        n4.child['c'-'a'] = n7;
        n4.child['d'-'a'] = n8;
        n5.child['d'-'a'] = n9;
        n6.child['g'-'a'] = n10;
        n7.isEnd = true;
        n8.isEnd = true;
        n9.isEnd = true;
        n10.isEnd = true;

        TrieNode n11 = new TrieNode();
        n7.child['d'-'a'] = n11;
        n11.isEnd = true;


        TrieNode.insert(root, "abfde");

        List<String> wordList = new ArrayList<>();
        TrieNode.getAllWords(root, new ArrayDeque<>(), wordList);
        System.out.println(wordList);

        boolean search = TrieNode.search(root, "abcde");
        System.out.println(search);

        boolean startWith = TrieNode.startWith(root, "ef");
        System.out.println(startWith);

//        TrieNode.preOrderTrie(root, 0);

    }

}

class TrieNode {
    //每个节点可以是26个字母中的任何一个，所以子节点有26种
    TrieNode[] child = new TrieNode[26];
    //默认不是结尾节点
    boolean isEnd = false;

    public static void preOrderTrie(TrieNode node, int layer) {
        if (node == null) {
            return;
        }
        //遍历26个子节点
        for (int i = 0; i < 26; i++) {
            if (node.child[i] == null) {
                continue;
            }
            //打印层数标记，用于显示。越是底层打印的字母越靠后
            for (int j = 0; j < layer; j++) {
                System.out.print("---");
            }
            if (node.child[i] != null) {
                System.out.printf("%c", i + 'a');
            }
            if (node.child[i].isEnd) {
                System.out.print("(end)");
            }
            System.out.println();
            preOrderTrie(node.child[i], layer + 1);
        }
    }


    /**
     * @auther 梁伟
     * @Description 子节点入栈，当子节点是end倒序打印栈中字符就是单词，将所有单词保存起来就是最终结果
     * @Date 2020/10/9 6:49
     * @Param [node, stack, wordList]
     * @return void
     **/
    public static void getAllWords(TrieNode node, Deque<Character> stack, List<String> wordList) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (node.child[i] == null) {
                continue;
            }
            //当前子节点字符入栈
            stack.push((char) (i + 'a'));
            //如果是end说明是一个单词的结尾，所以这时候应该将单词入最终结果集
            if (node.child[i].isEnd) {
                Iterator<Character> iterator = stack.descendingIterator();
                StringBuilder sb = new StringBuilder();
                while (iterator.hasNext()) {
                    sb.append(iterator.next());
                }
                //加入单词最终结果集
                wordList.add(sb.toString());
            }
            //处理当前子节点的下一层子节点
            getAllWords(node.child[i], stack, wordList);
            //当前子节点处理完成，弹出当前子节点
            stack.poll();
        }
    }


    public static void insert(TrieNode node, String word) {
        if (word.isEmpty()) {
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            //计算当前待插入字母对应在字典树中当前层的下标
            int pos = curr - 'a';
            //如果当前字母字典树中不存在，则插入
            if (node.child[pos] == null) {
                node.child[pos] = new TrieNode();
            }
            //node向下一层移动，插入下一个字母。即插入字母和层序相对应
            node = node.child[pos];
        }
        node.isEnd = true;
    }

    public static boolean search(TrieNode node, String word) {
        if (node == null || word.isEmpty()) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //计算当前字母在该层子树中处于第几个索引，并判断其是否存在
            int pos = word.charAt(i) - 'a';
            //如果该层不存在该字母，返回false
            if (node.child[pos] == null) {
                return false;
            }
            //字母移动到下一个，node下移一层
            node = node.child[pos];
        }
        //Word中的全部字母匹配上了后，判断最后一个字母是否是end，只有是end才说明该单词存在
        return node.isEnd;
    }

    /**
     * 查找是否存在指定前缀的单词,大致与search方法相同
     * @param node
     * @param prefix
     * @return
     */
    public static boolean startWith(TrieNode node, String prefix) {
        if (node == null || prefix.isEmpty()) {
            return false;
        }
        for (int i = 0; i < prefix.length(); i++) {
            int pos = prefix.charAt(i) - 'a';
            if (node.child[pos] == null) {
                return false;
            }
            node = node.child[pos];
        }
        return true;
    }
}