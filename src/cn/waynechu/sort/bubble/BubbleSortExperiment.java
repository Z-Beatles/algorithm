package cn.waynechu.sort.bubble;

/**
 * 冒泡排序算法
 * <p>
 * 最好时间复杂度：O(n)
 * 最差时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 稳定度：稳定
 * 空间复杂度：O(1)
 * <p>
 * 适用场景：
 * 1.排序元素较少时可使用
 *
 * @author waynechu
 * Created 2018-03-23 20:08
 */
public class BubbleSortExperiment {

    public static void bubbleSort(int[] numbers) {
        boolean didSwap;
        for (int i = numbers.length - 1; i > 0; i--) {
            didSwap = false;
            for (int j = 0; j < i; j++) {
                if (numbers[j + 1] > numbers[j]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = tmp;
                    didSwap = true;
                }
            }
            // 如果该趟扫描一次都没交换，说明元素已经有序，则无需进行后续几趟的扫描
            if (!didSwap) {
                return;
            }
        }
    }
}
