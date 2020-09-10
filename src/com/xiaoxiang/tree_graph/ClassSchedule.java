package com.xiaoxiang.tree_graph;

import java.util.*;

/**
 * author:w_liangwei
 * date:2020/9/10
 * Description: 课程表 LeetCode 207
 */
public class ClassSchedule {
    public static void main(String[] args) {
//        int[][] prerequisites = {{3, 0}, {3, 1}, {4, 1}, {4, 2}, {5, 3}, {5, 4}};
        int[][] prerequisites = {{0, 1}, {1, 2}, {2, 0}};
        boolean canFinish = canFinish(2, prerequisites);
        System.out.println(canFinish);
    }

    /**
     * @auther 梁伟
     * @Description 使用拓扑排序判断有向图是否有环    [[1,0],[0,1]]
     * @Date 2020/9/11 5:47
     * @Param [numCourses, prerequisites] 课程数，课程数组表示课程间的依赖关系
     * @return boolean
     **/
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //统计每个课程依赖了哪些课程。数据结构：课程号---依赖的课程列表
        Map<Integer, List<Integer>> inDegree = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            //当前课程如有依赖集合则添加，没有则新建
            Integer courseNo = prerequisites[i][0];
            List<Integer> prerequisiteCoursesList = inDegree.get(courseNo);
            if (prerequisiteCoursesList == null) {
                prerequisiteCoursesList = new ArrayList<>();
                prerequisiteCoursesList.add(prerequisites[i][1]);
                inDegree.put(courseNo, prerequisiteCoursesList);
            } else {
                prerequisiteCoursesList.add(prerequisites[i][1]);
            }
            //将被依赖课程号加入，防止该课程不依赖其它任何课程时，造成课程依赖关系的丢失
            if (!inDegree.containsKey(prerequisites[i][1])) {
                inDegree.put(prerequisites[i][1], new ArrayList<>());
            }
        }

        //上入度为0的课，也就是没有依赖其它课程的课
        int totalCourseNum = inDegree.size();
        Queue<Integer> queue = new LinkedList<>();
        Iterator<Integer> iterator = inDegree.keySet().iterator();
        while (iterator.hasNext()) {
            Integer courseNo = iterator.next();
            if (inDegree.get(courseNo).size() == 0) {
                queue.add(courseNo);
                //当前课程不依赖其它任何课程，所以移除依赖关系。
                //由于集合在遍历移除是会触发并发修改异常，所以要先使用迭代器移除key，然后再移除map中的元素
                iterator.remove();
                inDegree.remove(courseNo);
            }
        }

        //学习不依赖其它课程的课
        int count = 0;
        while (!queue.isEmpty() && count < totalCourseNum) {
            //学习该课程
            Integer courseNo = queue.poll();
            Iterator<Integer> iterator1 = inDegree.keySet().iterator();
            while (iterator1.hasNext()) {
                Integer key = iterator1.next();
                List<Integer> prerequisiteCoursesList = inDegree.get(key);
                //为其它依赖该课程的课程，由于学习完了该课程，需要去除依赖关系
                if (prerequisiteCoursesList.contains(courseNo)) {
                    prerequisiteCoursesList.remove(courseNo);
                }
                //依赖的课程列表为空，则将该课程加入队列进行学习
                if (prerequisiteCoursesList.size() == 0) {
                    queue.add(key);
                    iterator1.remove();
                    inDegree.remove(key);
                }
            }
            count++;
        }

        return count == totalCourseNum;
    }
}
