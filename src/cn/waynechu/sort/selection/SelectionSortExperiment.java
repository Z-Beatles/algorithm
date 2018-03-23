package cn.waynechu.sort.selection;

import java.util.Arrays;

/**
 * @author waynechu
 * Created 2018-03-23 17:26
 */
public class SelectionSortExperiment {

    public static void selectionSort(int[] numbers) {
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
