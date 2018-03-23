package cn.waynechu.sort.bubble;

import java.util.Arrays;

/**
 * @author waynechu
 * Created 2018-03-23 20:08
 */
public class BubbleSortExperiment {

    private static void bubbleSort(int[] numbers) {
        System.out.println(Arrays.toString(numbers));
        for (int i = numbers.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (numbers[j + 1] > numbers[j]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = tmp;
                }
            }
            System.out.println(Arrays.toString(numbers));
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{5, 4, 7, 1, 6, 2};
        bubbleSort(numbers);
    }
}
