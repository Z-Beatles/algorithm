package cn.waynechu.sort.bubble;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 20:08
 */
public class BubbleSortData {
    private int[] numbers;
    /**
     * [orderedIndex, numbers.length] 有序
     **/
    public int orderedIndex = -1;
    /**
     * 当前比较的元素中值较小的
     **/
    public int minCompareIndex = -1;
    /**
     * 当前比较的元素中值较大的
     **/
    public int maxCompareIndex = -1;

    public BubbleSortData(int n, int randomBound) {
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
