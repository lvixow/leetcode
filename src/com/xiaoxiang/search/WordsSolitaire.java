package com.xiaoxiang.search;
import java.util.*;

/**
 * @Date 2020/9/20 9:57
 * @Auther 梁伟
 * @Description 单词接龙 leetCode 126,127
 */
public class WordsSolitaire {
    public static void main(String[] args) {
        String[] arr = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(arr);
        int length = ladderLength("hit", "cog", wordList);
        System.out.println(length);
    }

    /**
     * @auther 梁伟
     * @Description 对单词每次改变不同位置的一个字母，如对第一个位置用26个字母组合，看哪个单词在WordList，则说明就是邻接的顶点
     * 可以构建一个图，求这两个顶点在图中的最短路径，可以使用BFS求解。使用双向BFS优化,分别从头尾同时进行
     * @Date 2020/9/20 20:22
     * @Param [beginWord, endWord, wordList]
     * @return int
     **/
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //将所有单词放入哈希表，方便用于判断组合后的单词是否是WordList中的单词
        HashSet<String> allWordList = new HashSet<>(wordList);

        //广度优先时需要使用队列辅助遍历
        LinkedList<String> headQueue = new LinkedList<>();
        //为了防止图中的环上的节点被重复访问造成死循环，我们使用哈希表保存访问过的节点
        HashSet<String> headVisited = new HashSet<>();

        //分别将头尾的单词加入队列，并设置为已访问
        headQueue.add(beginWord);
        headVisited.add(beginWord);

        //初始化步数，默认为1。因为已经加入了beginWord
        int step = 1;
        while (!headQueue.isEmpty()) {
            //获取当前层所有的顶点
            int currentTierSize = headQueue.size();
            //遍历当前层所有的单词
            for (int i = 0; i < currentTierSize; i++) {
                String current = headQueue.poll();
                //对单词的每个字母进行通配，判断通配后的单词在WordList中是否存在
                char[] chars = current.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    //保存原先该位置的字母，用于还原
                    char originalChar = chars[j];
                    //使用26个字母依次替换。则hit会变成三种方式：*it,h*t,hi*
                    for (char k = 'a'; k <= 'z'; k++) {
                        //要替换的字符和原来一样，则跳过
                        if (chars[j] == k) {
                            continue;
                        }
                        chars[j] = k;
                        //构造下一个词
                        String nextWord = new String(chars);
                        //判断该词是否是WordList中的词，不是的话就不符合要求
                        if (allWordList.contains(nextWord)) {
                            //判断构建的下一个词是否就是endWord，如果是则停止查找
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }
                            //当前词不是endWord，只是中间词,也没有被我们访问过。则加入下一层的查找
                            if (!headVisited.contains(nextWord)) {
                                //添加队列时必须设置为已经访问
                                headQueue.add(nextWord);
                                headVisited.add(nextWord);
                            }
                        }
                    }
                    //当处理完一个字母的不同后，处理下一个字母的不同时，要还原上一个字母。不然会造成多个字母不同，进而不满足要求
                    chars[j] = originalChar;
                }
            }
            //此时已经处理完了一层的所有词，相邻的层之间字符差一个，上边已经找到了下一层的所有词，所以步数加一
            step++;
        }
        return 0;
    }
}
