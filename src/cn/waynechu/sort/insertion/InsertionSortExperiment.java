package cn.waynechu.sort.insertion;

import cn.waynechu.sort.SortDataHelper;

import java.util.Arrays;

/**
 * 插入排序算法
 * <p>
 * 最好时间复杂度：O(n)
 * 最差时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 稳定度：稳定
 * 空间复杂度：O(1)
 * <p>
 * 适用场景：
 * 1.向已排序好的序列添加元素，此时退化为O(n)
 * 2.排序元素较少时可使用
 *
 * @author waynechu
 * Created 2018-03-23 22:39
 */
public class InsertionSortExperiment {

    private static void insertionSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int e = numbers[i];
            int j;
            for (j = i; j > 0 && numbers[j - 1] > e; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = e;
        }
    }

    /**
     * 用于对 numbers[left...right] 范围的数组进行插入排序
     *
     * @param numbers 数组
     * @param left    左边界
     * @param right   右边界
     */
    public static void insertionSort(int[] numbers, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int e = numbers[i];
            int j;
            for (j = i; j > left && numbers[j - 1] > e; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = e;
        }
    }

    public static void main(String[] args) {
        /*
         *    测试结果
         *    元素类型        元素个数          排序时间
         *    Random          100000 个         约为1700ms
         *    Random          50000 个          约为350ms
         *    Random          10000 个          约为25ms
         *    Random          1000 个           约为4ms
         *
         *    NearlyOrdered   100000 个         约为320~390ms
         *    NearlyOrdered   50000 个          约为90~110ms
         *    NearlyOrdered   10000 个          约为12~20ms
         *    NearlyOrdered   1000 个           约为2ms
         *
         *    Identical       100000 个         约为320~390ms
         *    Identical       50000 个          约为90~110ms
         *    Identical       10000 个          约为12~20ms
         *    Identical       1000 个           约为2ms
         */
        // 测试元素的数量
        int quantity = 1000;
        // 元素大小范围
        int randomBound = 1000;
        // 元素初始化类型 1.Random 随机元素  2.NearlyOrdered 趋近有序  3.Identical 大量相同
        SortDataHelper.Type dataType = SortDataHelper.Type.Random;
        SortDataHelper helper = new SortDataHelper(quantity, randomBound, dataType);
        int[] numbers = helper.createNumbers();

        // 统计算法耗时
        long startTime = System.currentTimeMillis();
        insertionSort(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        // 判断是否有序，检测算法正确性
        System.out.println(helper.isOrdered() ? "Status: Ordered." : "Status: Disorder!");
        System.out.println("Quantity: " + numbers.length);
        System.out.println("Sorted: " + Arrays.toString(numbers));
    }
}
