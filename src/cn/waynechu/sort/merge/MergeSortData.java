package cn.waynechu.sort.merge;

import java.util.Arrays;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-05-06 12:36
 */
public class MergeSortData {
    public enum Type {
        /** 随机大小 **/
        Random,
        /** 几乎有序 **/
        NearlyOrdered,
        /** 含有大量相同元素 **/
        Identical
    }

    private int[] numbers;
    public int left,right;
    public int mergeIndex;

    public MergeSortData(int n, int randomBound) {
        this(n, randomBound, Type.Random);
    }

    public MergeSortData(int n, int randomBound, Type dataType) {
        this.numbers = new int[n];
        Random random = new Random();

        // 生成元素完全相同的数组
        if (dataType == Type.Identical) {
            // 生成重复元素
            int avgBound = (1 + randomBound) * 2 / 3;
            for (int i = 0; i < n; i++) {
                numbers[i] = avgBound;
            }
            // 按10%的比例生成随机元素
            int randomTime = (int) (0.1 * n);
            for (int i = 0; i < randomTime; i++) {
                int randomIndex = random.nextInt(n);
                int randomValue = random.nextInt(randomBound) + 1;
                numbers[randomIndex] = randomValue;
            }
        } else {
            // 生成随机数组
            for (int i = 0; i < n; i++) {
                numbers[i] = random.nextInt(randomBound) + 1;
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
