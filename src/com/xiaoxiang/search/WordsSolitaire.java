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

//        String[] arr = {"hot","dot","dog","lot","log"};
//        List<String> wordList = Arrays.asList(arr);
//        int length = ladderLength("hit", "cog", wordList);
        System.out.println(length);

        List<List<String>> ladders = findLadders("hit", "cog", wordList);
        System.out.println(ladders);
    }

    /**
     * @auther 梁伟
     * @Description 对单词每次改变不同位置的一个字母，如对第一个位置用26个字母组合，看哪个单词在WordList，则说明就是邻接的顶点
     * 可以构建一个图，求这两个顶点在图中的最短路径，可以使用BFS求解。使用双向BFS优化,分别从头尾同时进行
     * @Date 2020/9/20 20:22
     * @Param [beginWord, endWord, wordList]
     * @return int  最短路径长度
     **/
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //单向BFS
        /*//将所有单词放入哈希表，方便用于判断组合后的单词是否是WordList中的单词
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
        return 0;*/



        //双向BFS，分别获取begin和end下一层的词，然后比较它两哪个词少，哪个少遍历哪个。
        //则会形成一会是begin从end走，一会是end从begin走，最终相遇了，则代表BFS完成

        //保存所有单词用于比较
        HashSet<String> allWords = new HashSet<>(wordList);
        //只有当endWord在WordList中才能找路径，否则路径不存在。就是说beginWord可以不在WordList，但是endWord必须在WordList中
        if (!allWords.contains(endWord)) {
            return 0;
        }

        //为两端准备队列
        LinkedList<String> headQueue = new LinkedList<>();
        LinkedList<String> tailQueue = new LinkedList<>();
        //记录访问过的节点，用于防止图中循环和查看是否已经相遇
        HashSet<String> headVisited = new HashSet<>();
        HashSet<String> tailVisited = new HashSet<>();

        headQueue.add(beginWord);
        tailQueue.add(endWord);
        headVisited.add(beginWord);
        tailVisited.add(endWord);


        int step = 1;
        while (!headQueue.isEmpty() && !tailQueue.isEmpty()) {
            //哪边的元素少，则处理哪边的。这样就达到了双向，方向随着元素的多少在变换，但不保证两边一边一次这样的顺序，
            //有可能一边执行的次数多，一边执行的少。这个和他们自己各边的下一层元素多少有关
            if (tailQueue.size() < headQueue.size()) {
                //交换队列和访问数组，两个都要交换才能保持一致
                LinkedList<String> temp = headQueue;
                headQueue = tailQueue;
                tailQueue = temp;

                HashSet<String> temp1 = headVisited;
                headVisited = tailVisited;
                tailVisited = temp1;
            }

            //获得该层词的个数
            int currentTierSize = headQueue.size();
            while (currentTierSize > 0) {
                //处理当前层的词，并对当前层词的个数减一
                currentTierSize--;
                String current = headQueue.poll();
                char[] chars = current.toCharArray();
                //每个字母用26个字母做替换
                for (int i = 0; i < chars.length; i++) {
                    //保存原先该位置的字母，用于还原
                    char originalChar = chars[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        //如果替换的字母和原来相同则跳过
                        if (chars[i] == j) {
                            continue;
                        }
                        chars[i] = j;
                        //获得下一层的词
                        String nextWord = new String(chars);
                        //判断该词是否在WordList中
                        if (allWords.contains(nextWord)) {
                            //如果该词已经访问过，则说明另一端已经访问过了。则BFS相遇了
                            if (tailVisited.contains(nextWord)) {
                                return step + 1;
                            }
                            //没有访问过，添加到下一层进行访问
                            if (!headVisited.contains(nextWord)) {
                                headQueue.add(nextWord);
                                headVisited.add(nextWord);
                            }
                        }
                    }
                    //对该位置字母进行恢复，便于下一位置字母的替换
                    chars[i] = originalChar;
                }
            }
            //一层的单词处理完成，层数加一。层数和步数是一致的
            step++;
        }
        return 0;
    }




    /**
     * 获得所有最短路径
     * 采用双向BFS获取到达endWord所经过的所有层之间的邻接关系，如hit---hot,git。这些邻接关系将用于记录最短路径
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //放入所有单词
        HashSet<String> allWords = new HashSet<>(wordList);

        //获取到达endWord所在层前的所有邻接点
        Map<String, Set<String>> allAdjoinRelation = bfs(allWords, beginWord, endWord);

        return null;
    }

    /**
     * 获得到达endword前的所有邻接关系
     * @param allWords
     * @param beginWord
     * @param endWord
     * @return
     */
    private static Map<String, Set<String>> bfs(HashSet<String> allWords, String beginWord, String endWord) {

        LinkedList<String> headQueue = new LinkedList<>();
        HashSet<String> headVisited = new HashSet<>();
        LinkedList<String> tailQueue = new LinkedList<>();
        HashSet<String> tailVisited = new HashSet<>();

        headQueue.add(beginWord);
        tailQueue.add(endWord);
        headVisited.add(beginWord);
        tailVisited.add(endWord);

        Map<String, Set<String>> result = new HashMap<>();
        //为了保证邻接词典的构建顺序是一定的，而BFS是双向的，所以需要判断是否转向
        boolean veer = true;
        while (!headQueue.isEmpty() && !tailQueue.isEmpty()) {
            //比较前后两端哪端处理的单词少，并切换到单词少的一端进行处理
            if (headQueue.size() > tailQueue.size()) {
                LinkedList<String> temp = headQueue;
                headQueue = tailQueue;
                tailQueue = temp;

                HashSet<String> temp1 = headVisited;
                headVisited = tailVisited;
                tailVisited = temp1;
                veer = !veer;
            }

            //处理当前层
            int currentSize = headQueue.size();
            while (currentSize > 0) {
                currentSize--;
                String currentWord = headQueue.poll();
                //处理每个位置的字母
                for (int i = 0; i < currentWord.length(); i++) {
                    char[] chars = currentWord.toCharArray();
                    //做备份
                    char orginChar = chars[i];
                    //做26个字母替换
                    for (char j = 'a'; j < 'z'; j++) {
                        if (orginChar == j) {
                            continue;
                        }
                        chars[i] = j;
                        String nextWord = new String(chars);
                        //nextWord在词典中
                        if (allWords.contains(nextWord)) {
                            //发生过转向则交换词的构建顺序
                            if (!veer) {
                                String temp = currentWord;
                                currentWord = nextWord;
                                nextWord = temp;
                            }
                            //加入到邻接关系
                            result.computeIfAbsent(currentWord, a -> new HashSet<>());
                            result.get(currentWord).add(nextWord);
                            //相遇了
                            if (tailVisited.contains(nextWord)) {
                                return result;
                            }
                            //没有访问过
                            if (!headVisited.contains(nextWord)) {
                                headQueue.add(nextWord);
                                headVisited.add(nextWord);
                            }
                        }
                    }
                    chars[i] = orginChar;
                }
            }
        }

        System.out.println(result);
        return null;
    }
}
