package cn.waynechu.sort.quick;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-25 14:24
 */
public class QuickSortData {
    private int[] numbers;
    /**
     * 当前正在处理的区间下标 [left, right]
     **/
    public int left, right;
    /**
     * 当前选取的基准数下标
     **/
    public int currentPivot;
    /**
     * 当前两侧扫描的元素下标
     **/
    public int currentLeftElement, currentRightElement;
    /**
     * 基准数归位状态，默认全为false
     **/
    public boolean[] fixedPivot;

    public QuickSortData(int n, int randomBound) {
        this.numbers = new int[n];
        this.fixedPivot = new boolean[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(randomBound) + 1;
            fixedPivot[i] = false;
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
