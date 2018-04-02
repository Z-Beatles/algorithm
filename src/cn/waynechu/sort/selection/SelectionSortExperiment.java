package cn.waynechu.sort.selection;

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

    public static void selectionSort(int[] numbers) {
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
        }
    }
}