package cn.waynechu.sort.quick.twoways;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 双路快速排序可视化
 *
 * @author waynechu
 * Created 2018-03-25 14:25
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private TwoWaysQuickSortData data;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 画面停留时间
     **/
    private int delay;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay) {
        this(sceneWidth, sceneHeight, n, delay, TwoWaysQuickSortData.Type.Random);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay, TwoWaysQuickSortData.Type dataType) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay must be large or equals 0  !");
        }
        // 初始化数据
        data = new TwoWaysQuickSortData(n, sceneHeight, dataType);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("双路快速排序可视化", sceneWidth, sceneHeight);
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
        setIndexData(-1, -1, -1, -1, -1, -1);
        quickSort2(0, data.getNumbers().length - 1);

        // 输出排序后的结果
        System.out.println(Arrays.toString(data.getNumbers()));
        setIndexData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort2(int left, int right) {
        if (left > right) {
            return;
        }
        if (left == right) {
            // 当前区间只有一个元素就直接作为排序好的基准数来显示
            setIndexData(left, right, -1, -1, -1, left);
        }
        // 绘制当前处理的区间
        setIndexData(left, right, -1, -1, -1, -1);

        // 产生随机的基准数 当元素大量重复，产生随机基准数的代码段可以删掉
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;

        // 绘制交换前选取的基准数
        setIndexData(left, right, randomIndex, -1, -1, -1);
        data.swap(left, randomIndex);
        int pivot = data.getNumbers()[left];
        // 绘制交换后选取的基准数
        setIndexData(left, right, left, -1, -1, -1);

        int i = left + 1, j = right;
        setIndexData(left, right, left, i, j, -1);
        while (true) {
            while (i <= right && data.getNumbers()[i] < pivot) {
                setIndexData(left, right, left, i, j, -1);
                i++;
                setIndexData(left, right, left, i, j, -1);
            }
            while (j >= left + 1 && data.getNumbers()[j] > pivot) {
                setIndexData(left, right, left, i, j, -1);
                j--;
                setIndexData(left, right, left, i, j, -1);
            }
            if (i > j) {
                break;
            }
            setIndexData(left, right, left, i, j, -1);
            data.swap(i, j);
            setIndexData(left, right, left, j, i, -1);
            i++;
            j--;
            setIndexData(left, right, left, i, j, -1);
        }
        data.swap(left, j);
        setIndexData(left, right, left, -1, -1, j);
        quickSort2(left, j - 1);
        quickSort2(j + 1, right);
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int left, int right, int currentPivot, int currentLeftElement, int currentRightElement, int fixedPivotIndex) {
        data.left = left;
        data.right = right;
        data.currentPivot = currentPivot;
        data.currentLeftElement = currentLeftElement;
        data.currentRightElement = currentRightElement;
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
        int delay = 20;
        // 初始化数组状态
        TwoWaysQuickSortData.Type dataType = TwoWaysQuickSortData.Type.Identical;

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay, dataType);
    }
}
