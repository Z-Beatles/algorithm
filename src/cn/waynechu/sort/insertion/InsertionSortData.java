package cn.waynechu.sort.insertion;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-24 13:31
 */
public class InsertionSortData {
    private int[] numbers;
    /**
     * [0, orderedIndex) 有序
     **/
    public int orderedIndex = -1;
    /**
     * 当前比较的元素
     **/
    public int currentIndex = -1;

    public InsertionSortData(int n, int randomBound) {
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
