package cn.waynechu.sort.heap;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-04-01 11:26
 */
public class HeapSortData {
    private int[] numbers;
    /**
     * number[heapIndex...numbers.length) 已排序
     **/
    public int heapIndex;

    public HeapSortData(int n, int randomBound) {
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
