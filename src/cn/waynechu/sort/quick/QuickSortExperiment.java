package cn.waynechu.sort.quick;

import java.util.Arrays;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-24 15:01
 */
public class QuickSortExperiment {

    /**
     * 普通单路快排 O(n * log2n) ~  O(n^2)
     * 若元素趋于有序，时间复杂度会退化到O(n^2)，使用随机基准数可优化分区 O(n * log2n)
     * 若元素大量相同，随机基准数算法会再次退化到O(n^2)，使用双路快排可
     **/
    private static void quickSort1(int[] numbers, int left, int right) {
        // 递归出口
        if (left > right) {
            return;
        }

        // 产生随机的基准数
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(numbers, left, randomIndex);

        // pivot中存放基准数
        int pivot = numbers[left];
        int i = left;
        // 分区 numbers[left+1...i] < pivot ; numbers[i+1...j] > pivot
        for (int j = left + 1; j <= right; j++) {
            if (numbers[j] < pivot) {
                i++;
                swap(numbers, i, j);
            }
        }
        // 基准数归位
        swap(numbers, left, i);

        quickSort1(numbers, left, i - 1);
        quickSort1(numbers, i + 1, right);
    }

    /**
     * 双路快排 O(n * log2n)
     * 用于含有元素大量重复的情况
     * 由于双路快排两个分区的大小差不多，所以还能一定程度上减少递归栈的深度
     **/
    private static void quickSort2(int[] numbers, int left, int right) {
        // 递归出口
        if (left > right) {
            return;
        }

        // 产生随机的基准数 当元素大量重复，产生随机基准数的代码段可以删掉
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(numbers, left, randomIndex);

        // pivot中存放基准数
        int pivot = numbers[left];
        int i = left + 1, j = right;
        while (true) {
            // 小于基准数就一直往前找，直到找到一个数等于基准数或大于基准数，
            while (i <= right && numbers[i] < pivot) {
                i++;
            }
            while (j >= left + 1 && numbers[j] > pivot) {
                j--;
            }
            if (i > j) {
                break;
            }
            // 交换两个元素的位置
            swap(numbers, i, j);
            // 指针向前移动一下
            i++;
            j--;
        }
        // 基准数归位
        swap(numbers, left, j);

        quickSort2(numbers, left, j - 1);
        quickSort2(numbers, j + 1, right);
    }

    private static void swap(int[] s, int a, int b) {
        int tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;
    }

    public static void main(String[] args) {
        int[] numbers = new int[10000];
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            // 1.生成无序数组
            //numbers[i] = random.nextInt(9999);

            // 2.生成有序数组
            numbers[i] = i;

            // 4.元素相同，高重复率
            //numbers[i] = 99;
        }
        // 3.生成趋近有序数组，按 1% 的比例将有序数组打乱
        int a, b;
        for (int n = 0; n < numbers.length * 0.01; n++) {
            a = random.nextInt(numbers.length);
            b = random.nextInt(numbers.length);
            swap(numbers, a, b);
        }

        // 1.1  【无序数组】  元素10000个  单路快排  时间约为3ms         使用随机基准数时间升为8ms
        // 1.2  【无序数组】  元素10000个  双路快排  时间约为3ms         使用随机基准数时间升为8ms
        // 2.1  【有序数组】  元素10000个  单路快排  时间约为25ms        使用随机基准数时间降为8ms
        // 2.2  【有序数组】  元素10000个  双路快排  时间约为35ms        使用随机基准数时间降为8ms
        // 3.1  【趋近有序】  元素10000个  单路快排  时间约为15ms        使用随机基准数时间降为8ms
        // 3.2  【趋近有序】  元素10000个  双路快排  时间约为30ms        使用随机基准数时间降为8ms
        // 4.1  【元素相同】  元素8000个   单路快排  时间约为20ms        使用随机基准数时间升为25ms
        // 4.2  【元素相同】  元素8000个   双路快排  时间约为2ms         使用随机基准数时间升为7ms
        //  当 元素趋于有序 时可以使用 随机基准数的方式 来优化分区
        //  当 元素重复率高 时可以使用   双路快排的方式 来优化分区
        long startTime = System.currentTimeMillis();
        quickSort2(numbers, 0, numbers.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(Arrays.toString(numbers));
    }
}
