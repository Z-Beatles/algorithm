package cn.waynechu.sort;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 用于生成各种类型的测试数据
 *
 * @author waynechu
 * Created 2018-03-26 16:08
 */
public class SortDataHelper {

    private SortDataHelper() {
    }

    /**
     * 生成元素为随机大小的数组
     *
     * @param size      数据个数
     * @param randomMin 数据最小值
     * @param randomMax 数据最大值
     * @return 数组
     */
    public static int[] generateRandomArray(int size, int randomMin, int randomMax) {
        int[] numbers = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(randomMax - randomMin + 1) + randomMin;
        }
        return numbers;
    }

    /**
     * 生成元素为几乎有序的数组
     *
     * @param size      数据个数
     * @param swapTimes 打乱的次数
     * @return 数组
     */
    public static int[] generateNearlyOrderedArray(int size, int swapTimes) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i;
        }
        // 随机交换
        Random random = new Random();
        for (int i = 0; i < swapTimes; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            swap(numbers, a, b);
        }
        return numbers;
    }

    /**
     * 生成元素为几乎相同的数组
     *
     * @param size        数据个数
     * @param changeTimes 修改次数
     * @return 数组
     */
    public static int[] generateIdenticalArray(int size, int changeTimes) {
        int[] numbers = new int[size];
        int avgValue = (1 + size) * 2 / 3;
        for (int i = 0; i < size; i++) {
            numbers[i] = avgValue;
        }
        Random random = new Random();
        for (int i = 0; i < changeTimes; i++) {
            int randomIndex = random.nextInt(size);
            int randomValue = random.nextInt(size);
            numbers[randomIndex] = randomValue;
        }
        return numbers;
    }

    /**
     * 交换数组中两个元素的位置
     **/
    public static void swap(int[] numbers, int index1, int index2) {
        int tmp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = tmp;
    }

    /**
     * 判断是否有序，用于检测算法正确性
     *
     * @return order ? true : false
     */
    public static boolean isOrdered(int[] numbers) {
        boolean ordered = true;
        // 从小到大
        if (numbers[0] <= numbers[numbers.length - 1]) {
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    ordered = false;
                }
            }
        } else {
            // 从大到小
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] < numbers[i + 1]) {
                    ordered = false;
                }
            }
        }
        return ordered;
    }

    /**
     * 打印排序算法的执行时间
     *
     * @param sortClassName 类名
     * @param methodName    方法名
     * @param params        参数列表
     */
    public static void testSort(String sortClassName, String methodName, Object... params) {
        // 使用Java的反射机制，通过排序的类名及方法名调用排序函数
        try {
            // 动态加载类
            Class sortClass = Class.forName(sortClassName);
            // 根据方法参数获取排序方法
            int[] numbers = (int[]) params[0];
            Method sortMethod;
            if (params.length == 1) {
                sortMethod = sortClass.getMethod(methodName, numbers.getClass());
            } else {
                sortMethod = sortClass.getMethod(methodName, numbers.getClass(), Integer.TYPE, Integer.TYPE);
            }
            // 反射
            long startTime = System.currentTimeMillis();
            // 调用排序的静态方法
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            // 排序结果正确则输出排序时间
            if (isOrdered(numbers)) {
                System.out.println(methodName + " : " + (endTime - startTime) + "ms");
            } else {
                System.out.println(sortClass.getSimpleName() + "排序结果不正确！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
