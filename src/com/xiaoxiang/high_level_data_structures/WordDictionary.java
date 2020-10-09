package com.xiaoxiang.high_level_data_structures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * author:w_liangwei
 * date:2020/10/9
 * Description: 词典 LeetCode 211
 *
 */
public class WordDictionary {

    //维护一个字典树的根结点
    static Node root;

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        List<String> wordList = new ArrayList<>();
        root.getAllWords(new ArrayDeque<>(), wordList);
        System.out.println(wordList);

        System.out.println(wordDictionary.search("pad"));// return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node node = root;
        if (word.isEmpty()) {
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            //计算当前待插入字母对应在字典树中当前层的下标
            int pos = curr - 'a';
            //如果当前字母字典树中不存在，则插入
            if (node.child[pos] == null) {
                node.child[pos] = new Node();
            }
            //node向下一层移动，插入下一个字母。即插入字母和层序相对应
            node = node.child[pos];
        }
        node.isEnd = true;
    }

    /**
     * 在原有字典树的基础上考虑对通配符进行处理
     * @param word
     * @return
     */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    /**
     * 在当前节点的子节点中查找当前字符
     * @param node 当前待处理节点
     * @param word 当前单词
     * @param index 当前处理的字符索引
     * @return
     */
    public boolean match(Node node, String word, int index) {
        //当要查找的index刚好越界，此时的node恰好是最后一个字母所对应的node。如果能走到最后一个字符说明前边的字符全部匹配上了，只需要判断最后一个字符是不是end，如果是end就是一个单词，不是说明就不是一个单词
        if (index == word.length()) {
            return node.isEnd;
        }
        char curr = word.charAt(index);
        //如果当前字母是 "." 那么直接跳到下一个字母判断
        if (curr == '.') {
            //跳过通配符，看node的子节点中是否有下一个字符
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null && match(node.child[i], word, index+1)) {
                    return true;
                }
            }
            return false;
        } else {
            //当前字符是a-z
            int pos = curr - 'a';
            if (node.child[pos] == null) {
                return false;
            }
            //匹配下一个字母
            return match(node.child[pos], word, index+1);
        }
    }
}


class Node {
    //每个节点可以是26个字母中的任何一个，所以子节点有26种
    Node[] child;
    //默认不是结尾节点
    boolean isEnd;

    public Node() {
        this.child = new Node[26];
        this.isEnd = false;
    }

    public void getAllWords(Deque<Character> stack, List<String> wordList) {
        Node node = this;
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
            node.child[i].getAllWords(stack, wordList);
            //当前子节点处理完成，弹出当前子节点
            stack.poll();
        }
    }
}

