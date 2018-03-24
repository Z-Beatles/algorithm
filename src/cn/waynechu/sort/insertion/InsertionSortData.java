package cn.waynechu.sort.insertion;

import java.util.Arrays;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-24 13:31
 */
public class InsertionSortData {
    public enum Type {
        /**
         * 随机大小
         **/
        Random,
        /**
         * 几乎有序
         **/
        NearlyOrdered,
        /**
         * 有序
         */
        Ordered
    }

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
        this(n, randomBound, Type.Random);
    }

    public InsertionSortData(int n, int randomBound, Type dataType) {
        this.numbers = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(randomBound) + 1;
        }
        // 生成有序的数组
        if (dataType == Type.Ordered) {
            Arrays.sort(numbers);
        }
        // 生成几乎有序的数组
        if (dataType == Type.NearlyOrdered) {
            Arrays.sort(numbers);
            int swapTime = (int) (0.03 * n);
            for (int i = 0; i < swapTime; i++) {
                int a = random.nextInt(n);
                int b = random.nextInt(n);
                swap(a, b);
            }
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
