package cn.waynechu.sort.quick.oneway;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 单路快速排序可视化
 *
 * @author waynechu
 * Created 2018-03-25 17:31
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private OneWayQuickSortData data;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 画面停留时间
     **/
    private int delay;
    /**
     * 是否随机化基准数
     **/
    private boolean randomPivot;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay) {
        this(sceneWidth, sceneHeight, n, delay, OneWayQuickSortData.Type.Random, false);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay, OneWayQuickSortData.Type dataType) {
        this(sceneWidth, sceneHeight, n, delay, dataType, false);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay, OneWayQuickSortData.Type dataType, boolean randomPivot) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay must be large or equals 0  !");
        }
        // 初始化数据
        data = new OneWayQuickSortData(n, sceneHeight, dataType);
        this.delay = delay;
        this.randomPivot = randomPivot;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("单路快速排序可视化", sceneWidth, sceneHeight);
            // 开启新线程执行动画逻辑
            new Thread(this::run).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        // 输出初始化数据大小
        System.out.println(Arrays.toString(data.getNumbers()));
        setIndexData(-1, -1, -1, -1, -1);
        quickSort1(0, data.getNumbers().length - 1);

        // 输出排序后的结果
        System.out.println(Arrays.toString(data.getNumbers()));
        setIndexData(-1, -1, -1, -1, -1);
    }

    private void quickSort1(int left, int right) {
        // 递归出口
        if (left > right) {
            return;
        }
        if (left == right) {
            // 当前区间只有一个元素就直接作为排序好的基准数来显示
            setIndexData(left, right, -1, -1, left);
        }
        // 绘制当前处理的区间
        setIndexData(left, right, -1, -1, -1);

        // 使用随机的基准数
        if (randomPivot) {
            Random random = new Random();
            int randomIndex = random.nextInt(right - left + 1) + left;

            // 绘制交换前选取的基准数
            setIndexData(left, right, randomIndex, -1, -1);
            data.swap(left, randomIndex);
        }

        int pivot = data.getNumbers()[left];
        // 绘制选取的基准数
        setIndexData(left, right, left, -1, -1);

        int i = left;
        // 分区 numbers[left+1...i] < pivot ; numbers[i+1...j] > pivot
        for (int j = left + 1; j <= right; j++) {
            setIndexData(left, right, left, j, -1);
            if (data.getNumbers()[j] < pivot) {
                i++;
                data.swap(i, j);
                setIndexData(left, right, left, j, -1);
            }
        }
        // 基准数归位
        data.swap(left, i);
        setIndexData(left, right, -1, -1, i);

        quickSort1(left, i - 1);
        quickSort1(i + 1, right);
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int left, int right, int currentPivot, int currentElement, int fixedPivotIndex) {
        data.left = left;
        data.right = right;
        data.currentPivot = currentPivot;
        data.currentElement = currentElement;
        if (fixedPivotIndex != -1) {
            data.fixedPivot[fixedPivotIndex] = true;
        }
        // 触发重绘
        frame.render(data);
        AlgoVisHelper.pause(delay);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        // 排序数的个数
        int n = 100;
        // 画面重绘延迟
        int delay = 40;
        // 初始化数组状态
        OneWayQuickSortData.Type dataType = OneWayQuickSortData.Type.Random;

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay, dataType, true);
    }
}
