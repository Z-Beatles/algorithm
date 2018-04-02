package cn.waynechu.sort;

import java.util.Arrays;

/**
 * @author waynechu
 * Created 2018-04-02 16:09
 */
public class Main {

    public static void main(String[] args) {
      /*
        测试结果如下
        Test for Random Array, size = 10000, randomBound=[0, 10000]
        bubbleSort : 221ms
        selectionSort : 63ms
        insertionSort : 17ms
        mergeSort : 3ms
        mergeSortBU : 4ms
        heapSort1 : 10ms
        heapSort2 : 2ms
        quickSort1 : 7ms
        quickSort2 : 5ms
        quickSort3 : 6ms

        Test for NearlyOrdered Array, size = 10000, swapTimes = 500
        bubbleSort : 128ms
        selectionSort : 67ms
        insertionSort : 7ms
        mergeSort : 1ms
        mergeSortBU : 3ms
        heapSort1 : 2ms
        heapSort2 : 1ms
        quickSort1 : 1ms
        quickSort2 : 0ms
        quickSort3 : 2ms

        Test for Identical Array, size = 10000, changeTimes = 500
        bubbleSort : 41ms
        selectionSort : 58ms
        insertionSort : 7ms
        mergeSort : 1ms
        mergeSortBU : 3ms
        heapSort1 : 2ms
        heapSort2 : 1ms
        quickSort1 : 27ms
        quickSort2 : 1ms
        quickSort3 : 0ms  */

        // --------------------------------------  测试随机元素 --------------------------------------
        // 测试元素的数量
        int size = 10000;
        int randomMin = 0;
        int[] numbers = SortDataHelper.generateRandomArray(size, randomMin, size);

        System.out.println("Test for Random Array, size = " + size + ", randomBound=[" + randomMin + ", " + size + "]");
        SortDataHelper.testSort("cn.waynechu.sort.bubble.BubbleSortExperiment", "bubbleSort", (Object) Arrays.copyOf(numbers, numbers.length));
        SortDataHelper.testSort("cn.waynechu.sort.selection.SelectionSortExperiment", "selectionSort", (Object) Arrays.copyOf(numbers, numbers.length));
        SortDataHelper.testSort("cn.waynechu.sort.insertion.InsertionSortExperiment", "insertionSort", (Object) Arrays.copyOf(numbers, numbers.length));
        SortDataHelper.testSort("cn.waynechu.sort.merge.MergeSortExperiment", "mergeSort", Arrays.copyOf(numbers, numbers.length), 0, numbers.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.merge.MergeSortExperiment", "mergeSortBU", (Object) Arrays.copyOf(numbers, numbers.length));
        SortDataHelper.testSort("cn.waynechu.sort.heap.HeapSortExperiment", "heapSort1", (Object) Arrays.copyOf(numbers, numbers.length));
        SortDataHelper.testSort("cn.waynechu.sort.heap.HeapSortExperiment", "heapSort2", (Object) Arrays.copyOf(numbers, numbers.length));
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort1", Arrays.copyOf(numbers, numbers.length), 0, numbers.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort2", Arrays.copyOf(numbers, numbers.length), 0, numbers.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort3", Arrays.copyOf(numbers, numbers.length), 0, numbers.length - 1);
        System.out.println();

        // --------------------------------------  测试趋近有序元素 --------------------------------------
        int swapTimes = (int) (size * 0.05);
        int[] numbers2 = SortDataHelper.generateNearlyOrderedArray(size, swapTimes);

        System.out.println("Test for NearlyOrdered Array, size = " + size + ", swapTimes = " + swapTimes);
        SortDataHelper.testSort("cn.waynechu.sort.bubble.BubbleSortExperiment", "bubbleSort", (Object) Arrays.copyOf(numbers2, numbers2.length));
        SortDataHelper.testSort("cn.waynechu.sort.selection.SelectionSortExperiment", "selectionSort", (Object) Arrays.copyOf(numbers2, numbers2.length));
        SortDataHelper.testSort("cn.waynechu.sort.insertion.InsertionSortExperiment", "insertionSort", (Object) Arrays.copyOf(numbers2, numbers2.length));
        SortDataHelper.testSort("cn.waynechu.sort.merge.MergeSortExperiment", "mergeSort", Arrays.copyOf(numbers2, numbers2.length), 0, numbers2.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.merge.MergeSortExperiment", "mergeSortBU", (Object) Arrays.copyOf(numbers2, numbers2.length));
        SortDataHelper.testSort("cn.waynechu.sort.heap.HeapSortExperiment", "heapSort1", (Object) Arrays.copyOf(numbers2, numbers2.length));
        SortDataHelper.testSort("cn.waynechu.sort.heap.HeapSortExperiment", "heapSort2", (Object) Arrays.copyOf(numbers2, numbers2.length));
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort1", Arrays.copyOf(numbers2, numbers2.length), 0, numbers2.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort2", Arrays.copyOf(numbers2, numbers2.length), 0, numbers2.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort3", Arrays.copyOf(numbers2, numbers2.length), 0, numbers2.length - 1);
        System.out.println();

        // --------------------------------------  测试大量相同元素 --------------------------------------
        int changeTimes = (int) (size * 0.05);
        int[] numbers3 = SortDataHelper.generateIdenticalArray(size, changeTimes);

        System.out.println("Test for Identical Array, size = " + size + ", changeTimes = " + changeTimes);
        SortDataHelper.testSort("cn.waynechu.sort.bubble.BubbleSortExperiment", "bubbleSort", (Object) Arrays.copyOf(numbers3, numbers3.length));
        SortDataHelper.testSort("cn.waynechu.sort.selection.SelectionSortExperiment", "selectionSort", (Object) Arrays.copyOf(numbers3, numbers3.length));
        SortDataHelper.testSort("cn.waynechu.sort.insertion.InsertionSortExperiment", "insertionSort", (Object) Arrays.copyOf(numbers3, numbers3.length));
        SortDataHelper.testSort("cn.waynechu.sort.merge.MergeSortExperiment", "mergeSort", Arrays.copyOf(numbers3, numbers3.length), 0, numbers3.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.merge.MergeSortExperiment", "mergeSortBU", (Object) Arrays.copyOf(numbers3, numbers3.length));
        SortDataHelper.testSort("cn.waynechu.sort.heap.HeapSortExperiment", "heapSort1", (Object) Arrays.copyOf(numbers3, numbers3.length));
        SortDataHelper.testSort("cn.waynechu.sort.heap.HeapSortExperiment", "heapSort2", (Object) Arrays.copyOf(numbers3, numbers3.length));
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort1", Arrays.copyOf(numbers3, numbers3.length), 0, numbers3.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort2", Arrays.copyOf(numbers3, numbers3.length), 0, numbers3.length - 1);
        SortDataHelper.testSort("cn.waynechu.sort.quick.QuickSortExperiment", "quickSort3", Arrays.copyOf(numbers3, numbers3.length), 0, numbers3.length - 1);
    }
}
