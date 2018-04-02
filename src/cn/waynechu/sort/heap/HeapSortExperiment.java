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

    /**
     * 原地构建堆
     * parent(i) = (i - 1) / 2
     * leftChild(i) =  2 * i + 1
     * rightChild(i) = 2 * i + 2
     * 最后一个非叶子节点 = (size -1) / 2
     **/
    public static void heapSort(int[] numbers) {
        int heapSize = numbers.length;
        // 构建一个最大堆，从最后一个非叶子节点开始进行heapify过程
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            shiftDown(numbers, heapSize, i);
        }
        for (int i = heapSize - 1; i > 0; i--) {
            // 把当前堆中最大的元素放到它合适的位置
            swap(numbers, 0, i);
            // 其余元素继续构建最大堆
            shiftDown(numbers, i, 0);
        }
    }

    private static void shiftDown(int[] numbers, int heapSize, int curr) {
        // 有子节点（左孩子）
        while (2 * curr + 1 < heapSize) {
            // 此轮循环中，numbers[curr]和arr[j]交换位置
            int j = 2 * curr + 1;
            // 右孩子大于左孩子
            if (j + 1 < heapSize && numbers[j + 1] > numbers[j]) {
                j += 1;
            }
            // 父节点大于两个子节点
            if (numbers[curr] >= numbers[j]) {
                break;
            }
            swap(numbers, curr, j);
            // 考察下一节点
            curr = j;
        }
    }

    private static void swap(int[] numbers, int index1, int index2) {
        int tmp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = tmp;
    }

    public static void main(String[] args) {
        /*
         *    测试结果
         *    元素类型        元素个数          排序时间
         *    1R 2N           100000 个          约为22ms
         *    1R 2N           50000 个           约为12ms
         *    1R 2N           10000 个           约为4ms
         *    1R 2N           1000 个            约为1ms
         *
         *    3I              100000 个          约为9ms
         *    3I              50000 个           约为6ms
         *    3I              10000 个           约为3ms
         *    3I              1000 个            约为0ms
         */
        // 测试元素的数量
        int quantity = 100000;
        // 元素大小范围
        int randomBound = 100000;
        // 元素初始化类型 1.Random 随机元素  2.NearlyOrdered 趋近有序  3.Identical 大量相同
        SortDataHelper.Type dataType = SortDataHelper.Type.Random;
        SortDataHelper helper = new SortDataHelper(quantity, randomBound, dataType);
        int[] numbers = helper.createNumbers();

        // 统计算法耗时
        long startTime = System.currentTimeMillis();
        heapSort(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        // 判断是否有序，检测算法正确性
        System.out.println(helper.isOrdered() ? "Status: Ordered." : "Status: Disorder!");
        System.out.println("Quantity: " + numbers.length);
        System.out.println("Sorted: " + Arrays.toString(numbers));
    }
}
