package cn.waynechu.sort.selection;

import java.util.Arrays;

/**
 * 选择排序算法
 * <p>
 * 最好时间复杂度：O(n^2)
 * 最差时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 稳定度：不稳定
 * 空间复杂度：O(1)
 * <p>
 * 适用场景：
 * 1.排序元素较少时可使用
 *
 * @author waynechu
 * Created 2018-03-23 17:26
 */
public class SelectionSortExperiment {

    private static void selectionSort(int[] numbers) {
        System.out.println(Arrays.toString(numbers));
        for (int i = 0; i < numbers.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = tmp;
            System.out.println(Arrays.toString(numbers));
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 4, 7, 3, 2, 5, 8, 6, 9};
        selectionSort(numbers);
    }
}
