package cn.waynechu.sort.quick;

import java.util.Arrays;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-24 15:01
 */
public class QuickSortExperiment {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 6, 2, 3, 1, 5, 7, 8};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 普通单路快排 O(n * log2n) ~ O(n^2)
     **/
    public static void quickSort(int[] numbers, int left, int right) {
        // 递归出口
        if (left >= right) {
            return;
        }

        // 划分区间
        int i = partition(numbers, left, right);
        // 处理左分区
        quickSort(numbers, left, i - 1);
        // 处理右分区
        quickSort(numbers, i + 1, right);
    }

    private static int partition(int[] numbers, int left, int right) {
        // 选择第一个元素作为标定点
        int pivot = numbers[left];
        int j = left;
        // 分区 numbers[left+1...j] < pivot ; numbers[j+1...i] >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (numbers[i] < pivot) {
                j++;
                swap(numbers, j, i);
            }
        }
        // 基准数归位
        swap(numbers, left, j);
        return j;
    }

    /**
     * 普通单路快排 O(n * log2n) ~ O(n^2)
     * 若元素趋于有序，时间复杂度会退化到O(n^2)，使用随机基准数可优化分区 O(n * log2n)
     * 若元素大量相同，随机基准数算法会再次退化到O(n^2)，使用双路快排可
     **/
    public static void quickSort1(int[] numbers, int left, int right) {
        // 递归出口
        if (left >= right) {
            return;
        }

        // 产生随机的基准数
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(numbers, left, randomIndex);

        // pivot中存放基准数
        int pivot = numbers[left];
        int i = left;
        // 分区 numbers[left+1...i] < pivot ; numbers[i+1...j] >= pivot
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
     * 能够应用于含有元素大量重复的情况
     * 由于双路快排两个分区的大小差不多，所以还能一定程度上减少递归栈的深度
     **/
    public static void quickSort2(int[] numbers, int left, int right) {
        // 递归出口
        if (left >= right) {
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
            // 小于基准数就一直往前找，直到找到一个数等于基准数或大于基准数
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

    /**
     * 三路快排 O(n * log2n)
     * 进一步优化含有大量重复元素时的情况，此时算法进化为O(n)级别
     **/
    public static void quickSort3(int[] numbers, int left, int right) {
        if (left >= right) {
            return;
        }

        // 产生随机的基准数
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(numbers, left, randomIndex);

        int pivot = numbers[left];
        int lt = left;
        int gt = right + 1;
        int i = left + 1;
        // 分区 numbers[left+1...lt] < v; numbers[lt + 1...i) = v; numbers[gt... right] > v
        while (i < gt) {
            if (numbers[i] < pivot) {
                swap(numbers, i, lt + 1);
                lt++;
                i++;
            } else if (numbers[i] > pivot) {
                swap(numbers, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        swap(numbers, left, lt);
        quickSort3(numbers, left, lt - 1);
        quickSort3(numbers, gt, right);
    }

    private static void swap(int[] s, int a, int b) {
        int tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;
    }
}
