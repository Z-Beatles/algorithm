package cn.waynechu.sort.selection;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 20:08
 */
public class SelectionSortData {
    private int[] numbers;
    /**
     * [0, orderedIndex) 有序
     **/
    public int orderedIndex = -1;
    /**
     * 当前比较的元素索引
     **/
    public int currentCompareIndex = -1;
    /**
     * 当前找到的最小元素索引
     **/
    public int currentMinIndex = -1;

    public SelectionSortData(int n, int randomBound) {
        this.numbers = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(randomBound) + 1;
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    /**
     * 交换index为i和j的两个number值
     **/
    public void swap(int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
