package cn.waynechu.sort.heap;

import cn.waynechu.sort.MaxHeap;
import cn.waynechu.sort.SortDataHelper;

import java.util.Arrays;

/**
 * 堆排序(最大堆实现)
 * <p>
 * 最好时间复杂度：O(n logN)
 * 最差时间复杂度：O(n logN)
 * 平均时间复杂度：O(n logN)
 * 稳定度：不稳定
 * 空间复杂度：O(1)
 * <p>
 * 适用场景：
 * 1.排序元素较多时可使用
 *
 * @author waynechu
 * Created 2018-04-01 10:04
 */
public class HeapSortExperiment {

    public static void heapSort1(int[] numbers) {
        int heapSize = numbers.length;
        MaxHeap maxHeap = new MaxHeap(heapSize);
        // 将n个元素逐一插入到一个空堆中，时间复杂度为O(n logN)
        for (int number : numbers) {
            maxHeap.insert(number);
        }
        for (int i = heapSize - 1; i >= 0; i--) {
            numbers[i] = maxHeap.extractMax();
        }
    }

    /**
     * 优化堆排序的堆构建方式
     **/
    public static void heapSort2(int[] numbers) {
        int heapSize = numbers.length;
        // 采用heapify调整堆的方式，时间复杂度为O(n)
        MaxHeap maxHeap = new MaxHeap(numbers);
        for (int i = heapSize - 1; i >= 0; i--) {
            numbers[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        /*
         *    测试结果
         *    元素类型        元素个数          排序时间
         *    1R 2N           100000 个          约为30ms
         *    1R 2N           50000 个           约为18ms
         *    1R 2N           10000 个           约为6ms
         *    1R 2N           1000 个            约为2ms
         *
         *    3I              100000 个          约为13ms
         *    3I              50000 个           约为10ms
         *    3I              10000 个           约为3ms
         *    3I              1000 个            约为1ms
         */
        // 测试元素的数量
        int quantity = 100000;
        // 元素大小范围
        int randomBound = 100000;
        // 元素初始化类型 1.Random 随机元素  2.NearlyOrdered 趋近有序  3.Identical 大量相同
        SortDataHelper.Type dataType = SortDataHelper.Type.Identical;
        SortDataHelper helper = new SortDataHelper(quantity, randomBound, dataType);
        int[] numbers = helper.createNumbers();

        // 统计算法耗时
        long startTime = System.currentTimeMillis();
        heapSort2(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        // 判断是否有序，检测算法正确性
        System.out.println(helper.isOrdered() ? "Status: Ordered." : "Status: Disorder!");
        System.out.println("Quantity: " + numbers.length);
        System.out.println("Sorted: " + Arrays.toString(numbers));
    }
}
