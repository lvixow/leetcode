package com.xiaoxiang.dynamic_programming;


/**
 * @Date 2020/9/28 6:46
 * @Auther 梁伟
 * @Description 最长上升子序列 LeetCode 300
 *
 * 状态转移方程：
 * 遍历到 nums[i] 时，需要把下标 i 之前的所有的数都看一遍；
 * 只要 nums[i] 严格大于在它位置之前的某个数，那么 nums[i] 就可以接在这个数后面形成一个更长的上升子序列；
 * 因此，dp[i] 就等于下标 i 之前严格小于 nums[i] 的状态值的最大者 +1+1+1。
 *
 *
 *
 * 一种是DP也就是动态规划，很简单，第i个元素之前的最小上升子序列的长度无非就是max(dp[i],dp[j]+1),那么另一种做法就是二分查找法，也很简单，无非就是再新建一个数组，
 * 然后第一个数先放进去，然后第二个数和第一个数比较，如果说大于第一个数，那么就接在他后面，如果小于第一个数，那么就替换，一般的，如果有i个数，那么每进来一个新的数，
 * 都要用二分查找法来得知要替换在哪个位置的数。因为有个for循环，所以是O(N),在加上循环里有个二分查找，所以最后是O(NlogN)的时间复杂度。
 *
 */
public class LongestRiseSubSequence {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,21,18};
        //1,3,6,7,9,10
        int length = lengthOfLIS(nums);
        System.out.println(length);
    }

    public static int lengthOfLIS(int[] nums) {
        /*int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        //每个以nums[i]结尾的最长子序列，最短的时候是只包含自己，所以长度为1
        //如果nums[i]没有比任何i之前的元素大，则最大上升子序列只包好自己
        Arrays.fill(dp, 1);
        //找到dp中最大的即为最大子序列长度
        int res = 0;
        //计算每一个dp[i]
        for (int i = 1; i < len; i++) {
            //比较当前数字nums[i]是否比i之前的哪个数字大，大的话就获取该数字的dp[j]然后加上自己构成dp[i]的上升子序列
            //可能和不同的dp[j]构成多个不同的上升子序列，所以需要比较哪个最长
            for (int j = 0; j < i; j++) {
                //比以nums[i]结尾的上升子序列大，则说明可以构成新的上升子序列
                if (nums[i] > nums[j]) {
                    //比较更子序列是否比原来的长度要长，长的话才更新
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //更新dp中的最大长度，也就是最终结果。省去了获得dp数组后又一次循环获取最大值的过程
            res = Math.max(res, dp[i]);
        }
        return res;*/



        //换一种递推方程：枚举所有长度从1到N的子序列，当存在多个相同长度的子序列时，则保证子序列最后一个数字最小
        //维护一个列表tails，其中每个元素tails[k]的值代表 长度为k+1的子序列尾部元素的值
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int left = 0, right = res;
            while(left < right) {
                int mid = (left + right) / 2;
                if(tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            //从while循环退出后，此时left和right相等
            tails[left] = num;
            if(res == right) {
                res++;
            }
        }
        return res;
    }
}
