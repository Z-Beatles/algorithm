package cn.waynechu.sort.merge;

import cn.waynechu.sort.SortDataHelper;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 最好时间复杂度：O(n logN)
 * 最差时间复杂度：O(n logN)
 * 平均时间复杂度：O(n logN)
 * 稳定度：稳定
 * 空间复杂度：O(n)
 * <p>
 * 适用场景：
 * 1.排序元素较多时可使用
 *
 * @author waynechu
 * Created 2018-03-24 14:56
 */
public class MergeSortExperiment {

    private static void mergeSort(int[] numbers, int left, int right) {

        if (left >= right) {
            return;
        }
        // 当元素较少时，使用插入排序来优化归并排序，但是在本机上测试实际上时间更长 - -。
//        if (right - left <= 15) {
//            InsertionSortExperiment.insertionSort(numbers, left, right);
//            return;
//        }

        // 递归分解为左右都有序两部分
        int mid = left + (right - left) / 2;
        mergeSort(numbers, left, mid);
        mergeSort(numbers, mid + 1, right);

        // 将 numbers[left, mid] 和 numbers[mid+1, right] 两部分进行归并
        if (numbers[mid] > numbers[mid + 1]) {
            merge(numbers, left, mid, right);
        }
    }

    private static void merge(int[] numbers, int left, int mid, int right) {
        // 创建临时数组
        int[] tmp = new int[right - left + 1];

        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (numbers[i] <= numbers[j]) {
                tmp[k++] = numbers[i++];
            } else {
                tmp[k++] = numbers[j++];
            }
        }
        // 把左边剩余的元素移入数组
        while (i <= mid) {
            tmp[k++] = numbers[i++];
        }
        // 把右边剩余的元素移入数组
        while (j <= right) {
            tmp[k++] = numbers[j++];
        }
        // 覆盖回原来的数组
        System.arraycopy(tmp, 0, numbers, left, tmp.length);
    }

    public static void main(String[] args) {
        /*
         *    测试结果
         *    元素类型        元素个数          排序时间
         *    ALL             100000 个          约为25~40ms
         *    ALL             50000 个           约为12~16ms
         *    ALL             10000 个           约为3ms
         *    ALL             1000 个           约为1ms
         */
        // 测试元素的数量
        int quantity = 50000;
        // 元素大小范围
        int randomBound = 50000;
        // 元素初始化类型 1.Random 随机元素  2.NearlyOrdered 趋近有序  3.Identical 大量相同
        SortDataHelper.Type dataType = SortDataHelper.Type.Random;
        SortDataHelper helper = new SortDataHelper(quantity, randomBound, dataType);
        int[] numbers = helper.createNumbers();

        // 统计算法耗时
        long startTime = System.currentTimeMillis();
        mergeSort(numbers, 0, numbers.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        // 判断是否有序，检测算法正确性
        System.out.println(helper.isOrdered() ? "Status: Ordered." : "Status: Disorder!");
        System.out.println("Quantity: " + numbers.length);
        System.out.println("Sorted: " + Arrays.toString(numbers));
    }
}
