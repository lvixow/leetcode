package com.xiaoxiang.search;
import java.util.*;

/**
 * @Date 2020/9/20 9:57
 * @Auther 梁伟
 * @Description 单词接龙 leetCode 126,127
 */
public class WordsSolitaire {
    public static void main(String[] args) {
//        String[] arr = {"hot","dot","dog","lot","log","cog"};
//        List<String> wordList = Arrays.asList(arr);
//        int length = ladderLength("hit", "cog", wordList);

//        String[] arr = {"hot","dot","dog","lot","log"};
//        List<String> wordList = Arrays.asList(arr);
//        int length = ladderLength("hit", "cog", wordList);
//        System.out.println(length);


        String[] arr = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(arr);
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
        //结果集
        List<List<String>> res = new ArrayList<>();
        //如果endWord不在wordList则没有路径
        if (!allWords.contains(endWord)) {
            return res;
        }

        //存放关系：每个单词可达的下层单词
        Map<String, List<String>> mapTree = new HashMap<>();
        HashSet<String> head = new HashSet<>(), tail = new HashSet<>();

        head.add(beginWord);
        tail.add(endWord);

        if (buildTree(allWords, head, tail, mapTree, true)) {
            dfs(mapTree, beginWord, endWord, res, new LinkedList<String>());
        }
        return res;
    }

    /**
     * @auther 梁伟
     * @Description 使用DFS回溯法多条寻找路径
     * @Date 2020/9/23 7:04
     * @Param [mapTree, beginWord, endWord, res, path]
     * @return void
     **/
    private static void dfs(Map<String, List<String>> mapTree, String beginWord, String endWord, List<List<String>> res, LinkedList<String> path) {
        //前进，添加当前位置到路径中
        path.add(beginWord);
        //如果路径已到达终点，则返回结果
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            //移除本次所走的位置，返回到上一层进行回溯
            path.removeLast();
            return;
        }
        //如果本次所走的位置，不是最终位置，则继续往前走
        if (mapTree.containsKey(beginWord)) {
            for (String word : mapTree.get(beginWord)) {
                dfs(mapTree, word, endWord, res, path);
            }
        }
        //回退，退出当前位置
        path.removeLast();
    }

    /**
     * @auther 梁伟
     * @Description
     * @Date 2020/9/23 6:29
     * @Param [allWords, head, tail, mapTree, isFront] 所有单词，从头出发的，从尾出发的，每个词可到达的下层的词，是否构建方向发生了变化
     * @return boolean 是否相遇了，没相遇则没有可到达endWord的路径
     **/
    private static boolean buildTree(HashSet<String> allWords, Set<String> head, Set<String> tail, Map<String, List<String>> mapTree, boolean isFront) {
        //当下一层没有词时停止探索
        if (head.size() == 0) {
            return false;
        }
        //转向从少的一边开始探索
        if (head.size() > tail.size()) {
            return buildTree(allWords, tail, head, mapTree, !isFront);
        }
        //因为要对head中的词做构建，所以从allWords中移除。代表已经构建过了，防止下次重复构建
        allWords.removeAll(head);
        //标记本层是否已到达目标单词
        boolean isMeet = false;
        //记录本层所访问的单词
        Set<String> nextLevel = new HashSet<>();
        for (String word : head) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String str = String.valueOf(chars);
                    if (allWords.contains(str)) {
                        //添加到一下层要访问的词
                        nextLevel.add(str);
                        //根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
                        //true: 从上往下探索：word -> str
                        //false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
                        String key = isFront ? word : str;
                        String nextWord = isFront ? str : word;
                        //判断是否遇见目标单词
                        if (tail.contains(str)) {
                            isMeet = true;
                        }
                        if (!mapTree.containsKey(key)) {
                            mapTree.put(key, new ArrayList<>());
                        }
                        //将可达词加入到关系映射
                        mapTree.get(key).add(nextWord);
                    }
                }
                chars[i] = temp;
            }
            //如果已经相遇了，则停止单词关系构建
            if (isMeet) {
                return true;
            }
        }
        return buildTree(allWords, nextLevel, tail, mapTree, isFront);
    }
}
