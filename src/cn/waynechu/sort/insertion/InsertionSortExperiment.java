package cn.waynechu.sort.insertion;

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

    public static void insertionSort(int[] numbers) {
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
}