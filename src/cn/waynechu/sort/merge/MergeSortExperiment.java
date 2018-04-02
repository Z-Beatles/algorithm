package cn.waynechu.sort.merge;

/**
 * 归并排序
 * <p>
 * 最好时间复杂度：O(n logN)
 * 最差时间复杂度：O(n logN)
 * 平均时间复杂度：O(n logN)
 * 稳定度：稳定
 * 空间复杂度：O(n)
 * <p>
 * 适用场景：
 * 1.排序元素较多时可使用
 *
 * @author waynechu
 * Created 2018-03-24 14:56
 */
public class MergeSortExperiment {

    /**
     * 迭代实现的自底向上的归并排序
     */
    public static void mergeSortBU(int[] numbers) {
        for (int size = 1; size <= numbers.length; size += size) {
            for (int i = 0; i + size < numbers.length; i += size + size) {
                // 对 numbers[i...i+size-1] 和 numbers[i+size...i+2*size-1] 进行归并
                merge(numbers, i, i + size - 1, Math.min(i + size + size - 1, numbers.length - 1));
            }
        }
    }

    /**
     * 递归实现的自顶向下的归并排序(更优)
     */
    public static void mergeSort(int[] numbers, int left, int right) {
        if (left >= right) {
            return;
        }
        // 当元素较少时，使用插入排序来优化归并排序，但是在本机上测试实际上时间更长 - -。
//        if (right - left <= 15) {
//            InsertionSortExperiment.insertionSort(numbers, left, right);
//            return;
//        }

        // 递归分解为左右都有序两部分
        int mid = left + (right - left) / 2;
        mergeSort(numbers, left, mid);
        mergeSort(numbers, mid + 1, right);

        // 将 numbers[left, mid] 和 numbers[mid+1, right] 两部分进行归并
        if (numbers[mid] > numbers[mid + 1]) {
            merge(numbers, left, mid, right);
        }
    }

    private static void merge(int[] numbers, int left, int mid, int right) {
        // 创建临时数组
        int[] tmp = new int[right - left + 1];

        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (numbers[i] <= numbers[j]) {
                tmp[k++] = numbers[i++];
            } else {
                tmp[k++] = numbers[j++];
            }
        }
        // 把左边剩余的元素移入数组
        while (i <= mid) {
            tmp[k++] = numbers[i++];
        }
        // 把右边剩余的元素移入数组
        while (j <= right) {
            tmp[k++] = numbers[j++];
        }
        // 覆盖回原来的数组
        System.arraycopy(tmp, 0, numbers, left, tmp.length);
    }
}
