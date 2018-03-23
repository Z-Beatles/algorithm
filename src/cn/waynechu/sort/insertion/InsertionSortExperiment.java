package cn.waynechu.sort.insertion;

import java.util.Arrays;

/**
 * 插入排序算法
 *
 * @author waynechu
 * Created 2018-03-23 22:39
 */
public class InsertionSortExperiment {

    private static void insertionSort(int[] numbers) {
        System.out.println(Arrays.toString(numbers));
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                if (numbers[j -1 ] > numbers[j]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = tmp;
                }
            }
            System.out.println(Arrays.toString(numbers));
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 4, 7, 3, 2, 5, 8, 6, 9};
        insertionSort(numbers);
    }
}
