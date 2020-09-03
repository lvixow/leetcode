package com.xiaoxiang.greedy;

import java.util.PriorityQueue;

/**
 * author:w_liangwei
 * date:2020/9/3
 * Description: 最少加油次数 LeetCode871
 */
public class RefuelingNum {
    public static void main(String[] args) {
        int[][] stations1 = {{10,60},{20,30},{30,30},{60,40}};
        int[][] stations2 = {{}};
        int[][] stations3 = {{10, 100}};
        int minRefuelStops = minRefuelStops(100, 10, stations1);
        System.out.println(minRefuelStops);
    }

    /**
     * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
     * 输出：2
     * 解释：我们出发时有 10 升燃料。我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
     * 然后，我们从 10 英里处的加油站开到60英里处的加油站（消耗 50 升燃料），并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
     * 我们沿途在1两个加油站停靠，所以返回2
     * 贪心规律：车在发现油不够的时候才进行加油，并且选一个途径加油量最多的加油站
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }

        //剩余油量
        int fuelRemain = startFuel;
        //用一个最大堆来保存油量，方便加入最大油量
        PriorityQueue<Integer> passAllFillStationOilMass = new PriorityQueue<>();
        //加油次数
        int count = 0;

        for (int i = 0; i < stations.length; i++) {
            //当前位置距离下一个加油站的距离或者是终点距离
            //当目标位置在下一个加油站之前，则更新下一次要到达位置为最近位置。这是对到达终点的处理
            int distance = target < stations[i][0] ? target : stations[i][0];
            //堆不为空时说明已经经过了加油站，当为空时则说明没有经过加油站或者是把沿途路过的每个加油站的油都加没了，为空时仍油不够，则无法到达下一个加油站
            //当发现剩余油量不够到达下一个加油站时则进行加油
            while (!passAllFillStationOilMass.isEmpty() && fuelRemain < distance) {
                //加一个所经过加油站的最大油量
                fuelRemain = fuelRemain + passAllFillStationOilMass.poll();
                count++;
            }

            //加油过后油量仍不够到达下一个加油站
            if (distance > fuelRemain) {
                return -1;
            }

            //前进
            //更新剩余油量
            fuelRemain = fuelRemain - distance;
            //更新剩余距离
            target = target - distance;
            //这个加油站所能提供的油量，并将油量放入堆中
            passAllFillStationOilMass.add(stations[i][1]);
        }
        return count;
    }
}
