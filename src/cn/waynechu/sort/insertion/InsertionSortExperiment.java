package cn.waynechu.sort.insertion;

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
 * 1.向已排序好的序列添加元素
 * 2.排序元素较少时可使用
 *
 * @author waynechu
 * Created 2018-03-23 22:39
 */
public class InsertionSortExperiment {

    private static void insertionSort(int[] numbers) {
        System.out.println(Arrays.toString(numbers));
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0 && numbers[j - 1] > numbers[j]; j--) {
                int tmp = numbers[j];
                numbers[j] = numbers[j - 1];
                numbers[j - 1] = tmp;
            }
            System.out.println(Arrays.toString(numbers));
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 4, 7, 3, 2, 5, 8, 6, 9};
        insertionSort(numbers);
    }
}
