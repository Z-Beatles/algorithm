package cn.waynechu.sort.quick;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-24 15:01
 */
public class QuickSortExperiment {

    /**
     * 普通单路快排 若元素趋于有序，时间复杂度会退化到O(n^2)
     **/
    private static void quickSort1(int[] numbers, int left, int right) {
        // 递归出口
        if (left > right) {
            return;
        }

        // point中存放基准数
        int point = numbers[left];
        int i = left;
        // numbers[left+1...i] < point ; numbers[i+1...j] > point
        for (int j = left + 1; j <= right; j++) {
            if (numbers[j] < point) {
                i++;
                int tmp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = tmp;
            }
        }
        // 基准数归位
        int tmp = numbers[left];
        numbers[left] = numbers[i];
        numbers[i] = tmp;

        quickSort1(numbers, left, i - 1);
        quickSort1(numbers, i + 1, right);
    }

    /**
     * 双路快排 O(n * log2n)
     **/
    private static void quickSort2(int[] numbers, int left, int right) {
        // 递归出口
        if (left > right) {
            return;
        }

        // point中存放基准数
        int point = numbers[left];
        int i = left, j = right;
        while (i != j) {
            while (numbers[j] >= point && i < j) {
                j--;
            }
            while (numbers[i] <= point && i < j) {
                i++;
            }
            // 交换两个元素的位置
            if (i < j) {
                int tmp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = tmp;
            }
        }
        // 基准数归位
        numbers[left] = numbers[i];
        numbers[i] = point;

        quickSort2(numbers, left, i - 1);
        quickSort2(numbers, i + 1, right);
    }

    public static void main(String[] args) {
        int[] numbers = new int[15000];
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            // 1.生成无序数组
            //numbers[i] = random.nextInt(9999);

            // 2.生成有序数组
            numbers[i] = i;

            // 4.元素相同，高重复率
            //numbers[i] = 99;
        }
        // 3.生成趋近有序数组，按 1% 的比例打乱顺序
        int a, b, tmp;
        for (int n = 0; n < numbers.length * 0.01; n++) {
            a = random.nextInt(numbers.length);
            b = random.nextInt(numbers.length);
            tmp = numbers[a];
            numbers[a] = numbers[b];
            numbers[b] = tmp;
        }

        // 1.1  【无序数组】  元素个数15000个  单路快排  时间约为4ms
        // 1.2  【无序数组】  元素个数15000个  双路快排  时间约为5ms
        // 2.1  【有序数组】  元素个数15000个  单路快排  时间约为45ms
        // 2.2  【有序数组】  元素个数15000个  双路快排  时间约为160ms
        // 3.1  【趋近有序】  元素个数15000个  单路快排  时间约为15~40ms
        // 3.2  【趋近有序】  元素个数15000个  双路快排  时间约为35ms
        // 4.1  【元素相同】  元素个数15000个  单路快排  时间约为45ms
        // 4.2  【元素相同】  元素个数15000个  双路快排  时间约为160ms
        long startTime = System.currentTimeMillis();
        quickSort2(numbers, 0, numbers.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
