package cn.waynechu.sort.quick;

import cn.waynechu.sort.SortDataHelper;

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
        if (left >= right) {
            return;
        }

        // 产生随机的基准数
//        Random random = new Random();
//        int randomIndex = random.nextInt(right - left + 1) + left;
//        swap(numbers, left, randomIndex);

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
     * 能够应用于含有元素大量重复的情况
     * 由于双路快排两个分区的大小差不多，所以还能一定程度上减少递归栈的深度
     **/
    private static void quickSort2(int[] numbers, int left, int right) {
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

    /**
     * 三路快排 O(n * log2n)
     * 进一步优化含有大量重复元素时的情况，此时算法进化为O(n)级别
     **/
    private static void quickSort3(int[] numbers, int left, int right) {
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

    public static void main(String[] args) {
        // 测试结果
        // 1.1  【随机元素】  元素100000个  单路快排  时间约为25ms         使用随机基准数时间约为45ms
        // 1.2  【随机元素】  元素100000个  双路快排  时间约为25ms         使用随机基准数时间约为45ms
        // 1.3  【随机元素】  元素100000个  三路快排  时间约为25ms         使用随机基准数时间约为45ms

        // 2.1  【趋近有序】  元素100000个  单路快排  时间约为35ms         使用随机基准数时间约为45ms
        // 2.2  【趋近有序】  元素100000个  双路快排  时间约为35ms         使用随机基准数时间约为45ms
        // 2.3  【趋近有序】  元素100000个  三路快排  时间约为35ms         使用随机基准数时间约为45ms

        // 3.1  【大量相同】  元素100000个  单路快排  栈溢出               栈溢出
        // 3.1  【大量相同】  元素15000个   单路快排  80ms                 80ms
        // 3.2  【大量相同】  元素100000个  双路快排  时间约为20ms         使用随机基准数时间约为35ms
        // 3.3  【大量相同】  元素100000个  三路快排  时间约为4ms          使用随机基准数时间约为6ms

        // 测试元素的数量
        int quantity = 100000;
        // 元素大小范围
        int randomBound = 100000;
        // 元素初始化类型 1.Random 随机元素  2.NearlyOrdered 趋近有序  3.Identical 大量相同
        SortDataHelper.Type dataType = SortDataHelper.Type.Random;
        SortDataHelper helper = new SortDataHelper(quantity, randomBound, dataType);
        int[] numbers = helper.createNumbers();

        // 统计算法耗时
        long startTime = System.currentTimeMillis();
        quickSort3(numbers, 0, numbers.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        // 判断是否有序，检测算法正确性
        System.out.println(helper.isOrdered() ? "Status: Ordered." : "Status: Disorder!");
        System.out.println("Quantity: " + numbers.length);
        System.out.println("Sorted: " + Arrays.toString(numbers));
    }
}
