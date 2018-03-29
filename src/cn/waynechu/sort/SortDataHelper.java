package cn.waynechu.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 用于生成各种类型的测试数据
 *
 * @author waynechu
 * Created 2018-03-26 16:08
 */
public class SortDataHelper {
    private int[] numbers;

    public SortDataHelper(int n, int randomBound) {
        this(n, randomBound, Type.Random);
    }

    public SortDataHelper(int n, int randomBound, Type dataType) {
        this.numbers = new int[n];
        Random random = new Random();

        // 生成含大量相同元素的数组
        if (dataType == Type.Identical) {
            // 生成重复元素
            int avgBound = (1 + randomBound) * 2 / 3;
            for (int i = 0; i < n; i++) {
                numbers[i] = avgBound;
            }
            // 按 5% 的比例生成随机大小元素
            int randomTime = (int) (0.05 * n);
            for (int i = 0; i < randomTime; i++) {
                int randomIndex = random.nextInt(n);
                int randomValue = random.nextInt(randomBound) + 1;
                numbers[randomIndex] = randomValue;
            }
        } else {
            // 生成随机元素
            for (int i = 0; i < n; i++) {
                numbers[i] = random.nextInt(randomBound) + 1;
            }
            // 生成 5% 的比例打乱有序数组
            if (dataType == Type.NearlyOrdered) {
                Arrays.sort(numbers);
                int swapTime = (int) (0.05 * n);
                for (int i = 0; i < swapTime; i++) {
                    int a = random.nextInt(n);
                    int b = random.nextInt(n);
                    swap(a, b);
                }
            }
        }
    }

    public int[] createNumbers() {
        return numbers;
    }

    /**
     * 判断是否有序，用于检测算法正确性
     *
     * @return order ? true : false
     */
    public boolean isOrdered() {
        boolean ordered = true;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                ordered = false;
            }
        }
        return ordered;
    }


    /**
     * 交换index为i和j的两个number值
     **/
    private void swap(int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }

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
         * 含有大量相同元素
         **/
        Identical
    }
}
