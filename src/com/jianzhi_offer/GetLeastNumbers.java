package com.jianzhi_offer;

import java.util.Arrays;

/**
 * author:w_liangwei
 * date:2021/1/25
 * Description: offer 40
 * 思路导引：最先想到的肯定是排序，排好了直接取前k个，但是需要对元素全部排序，所以进一步降低复杂度，对元素进行部分排序，即使用插入或冒泡只对前k个元素进行排序，但是这样复杂度还是高，
 * 因为我们没必要对前K个元素必须要排序，所以可以进一步降低复杂度，选择快排的变种--快速选择算法，即不执行递归的快排 + 二分查找，当快排后基准数的位置是k-1的时候，那么基准数前边的都比
 * 该位置基准数小，那么就是前k个元素。通过二分查找可以快速找到这样的基准数在数组中的位置。此种方法可以不需要额外空间，原地交换。但需要一次加载全部数组，数组过大时不能使用。这时可以
 * 使用大顶堆实现
 *
 * 大顶堆与快选比较
 * 1. 快选：时间复杂度和空间复杂度好，但不适用于大的数组
 * 2. 大顶堆：时间复杂度稍差，但是适用于大的数组排序，可以从硬盘加载数组进行大规模查找
 *
 *
 * 快速选择
 *  1. 单次快速选择确定基准数位置
 *  2. 当基准数的位置是k-1时说明找到了前k个
 *  3. 基准数位置比k-1小，说明右侧还有我们要找的元素，所以查找右边的区间
 *  4. 基准数位置比k-1大，说明左侧的区间包含前k个元素和其它不是前k个的元素，所以需要进一步向左查找缩小区间范围
 *
 *
 * topK，不难；其思路优化过程，不简单：
 *     全局排序，O(n*lg(n))
 *     局部排序，只排序TopK个数，O(n*k)
 *     堆，TopK个数也不排序了，O(n*lg(k))
 *     分治法，每个分支“都要”递归，例如：快速排序，O(n*lg(n))
 *     减治法，“只要”递归一个分支，例如：二分查找O(lg(n))，随机选择O(n)
 *     TopK的另一个解法：随机选择+partition
 *
 *
 * 一个对思路很有启发的地址
 *  https://www.sohu.com/a/255145095_178889
 *  http://blog.chinaunix.net/uid-26548237-id-3513260.html
 */
public class GetLeastNumbers {
    public static void main(String[] args) {
        int[] arr = {0,0,2,3,2,1,1,2,0,4};
        int[] leastNumbers = getLeastNumbers(arr, 4);
        System.out.println(Arrays.toString(leastNumbers));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k ==0 || arr.length == 0) {
            return new int[0];
        } else if (arr.length < k) {
            return arr;
        }
        //k个数时，基准数的索引应该是k-1，这样取到k-1时正好是K个数
        partitionArray(arr, 0, arr.length - 1, k -1);
        return Arrays.copyOfRange(arr, 0, k);
    }

    private static void partitionArray(int[] arr, int left, int right, int k) {
        //baseNumIndex是基准数经过一次排序后所在的位置，它将left到right间的元素分为了大于该基准数和小于该基准数
       int baseNumIndex = partition(arr, left, right);
       //当基准数的位置就是k，那么说明找到了这些数
        if (k == baseNumIndex) {
            return;
        } else if (k < baseNumIndex) {
            //当基准数的位置大于k，应该向比当前基准数小的方向去找topK，所以向左去划分数组
            partitionArray(arr, left, baseNumIndex - 1, k);
        } else {
            //当基准数位置小于k，说明m前边的都是topK中的，m后边还有一部分也是topK中的，需要继续向右划分数组。m作为基准数已经找到了正确的位置，所以两边都不需要加入下一轮的排序
            partitionArray(arr, baseNumIndex + 1, right, k);
        }
    }

    /**
     * 快速排序每次选取一个元素作为基准，每次能够确定一个基准元素的位置，当我们选取的基准元素经过一次快速排序后恰好在k-1的索引位置处，那么这个基准元素和它前边的所有元素都是前k个元素，
     * 这是由快排的性质决定的，即一次排序完成后，比基准元素大的都在它的右边，比基准元素小的都在左边
     *
     * 这是一次快速排序函数，即每次将数组left到right区间中的left作为基准元素，进行一次快排，并返回基准元素排序后的位置
     *
     * 将左边作为基准数，拿走基准数相当于挖了一个坑，然后右边跑，找到一个小于基准数的数去填左边的坑。这样左边没坑了，右边就形成了一个坑，然后左边去找一个大于基准数的数去填右边的坑，
     * 如此往复直到指针相遇，最后将基准数放到相遇的位置，完成一个基准数的放置
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        int baseNum = arr[left];
        int i = left;
        int j = right;
        //当指针相遇退出循环，最后i==j
        while (i < j) {
            //必须先从右边开始，因为我们选取了左边为基准数，先从准备开始交换时会覆盖右边，先从右边开始则可以覆盖基准数，因为我们已经保存了基准数
            while (i < j && arr[j] >= baseNum) {
                j--;
            }
            //右边交换到左边
            arr[i] = arr[j];
            while (i < j && arr[i] <= baseNum) {
                i++;
            }
            //左边交换到右边
            arr[j] = arr[i];
        }
        //最后i==j，此时的位置是基准数的位置
        arr[j] = baseNum;
        return j;
    }
}
