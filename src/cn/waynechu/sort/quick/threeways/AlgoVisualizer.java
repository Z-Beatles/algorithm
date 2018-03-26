package cn.waynechu.sort.quick.threeways;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 三路快速排序可视化
 *
 * @author waynechu
 * Created 2018-03-26 13:21
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private ThreeWaysQuickSortData data;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 画面停留时间
     **/
    private int delay;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay) {
        this(sceneWidth, sceneHeight, n, delay, ThreeWaysQuickSortData.Type.Random);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay, ThreeWaysQuickSortData.Type dataType) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay must be large or equals 0  !");
        }
        // 初始化数据
        data = new ThreeWaysQuickSortData(n, sceneHeight, dataType);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("三路快速排序可视化", sceneWidth, sceneHeight);
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
        setIndexData(-1, -1, -1, -1, -1, data.getNumbers().length + 1, -1);
        quickSort3(0, data.getNumbers().length - 1);

        // 输出排序后的结果
        System.out.println(Arrays.toString(data.getNumbers()));
        setIndexData(-1, -1, -1, -1, -1, data.getNumbers().length + 1, -1);
    }

    private void quickSort3(int left, int right) {
        if (left > right) {
            return;
        }
        if (left == right) {
            setIndexData(left, right, -1, -1, -1, data.getNumbers().length + 1, left);
            return;
        }

        // 产生随机的基准数
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;
        // 渲染交换前的pivot
        setIndexData(left, right, randomIndex, -1, -1, data.getNumbers().length + 1, -1);
        data.swap(left, randomIndex);
        int pivot = data.getNumbers()[left];
        // 渲染交换后的pivot
        setIndexData(left, right, left, -1, -1, data.getNumbers().length + 1, -1);

        // 分区 numbers[left+1...lt] < v; numbers[lt + 1...i) = v; numbers[gt... right] > v
        int lt = left;
        int gt = right + 1;
        int i = left + 1;
        setIndexData(left, right, left, i, lt, gt, -1);

        while (i < gt) {
            if (data.getNumbers()[i] < pivot) {
                data.swap(i, lt + 1);
                lt++;
                i++;
            } else if (data.getNumbers()[i] > pivot) {
                data.swap(i, gt - 1);
                gt--;
            } else {
                i++;
            }
            setIndexData(left, right, left, i, lt, gt, -1);
        }
        // 基准数归位
        data.swap(left, lt);
        setIndexData(left, right, left, i, lt, gt, lt);

        quickSort3(left, lt - 1);
        quickSort3(gt, right);
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int left, int right, int currentPivot, int currentElement, int lessThan, int greaterThan, int fixedPivot) {
        data.left = left;
        data.right = right;
        data.currentPivot = currentPivot;
        data.currentElement = currentElement;
        data.lessThan = lessThan;
        data.greaterThan = greaterThan;
        // 传来的fixedPivot是等于pivot的第一个元素
        if (fixedPivot != -1) {
            data.fixedPivot[fixedPivot] = true;
            // 如果fixedPivot后面的元素等于pivot则全部显示为已找到正确位置的元素
            for (int i = fixedPivot; i < data.getNumbers().length && data.getNumbers()[i] == data.getNumbers()[fixedPivot]; i++) {
                data.fixedPivot[i] = true;
            }
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
        int delay = 100;
        // 初始化数组状态
        ThreeWaysQuickSortData.Type dataType = ThreeWaysQuickSortData.Type.NearlyOrdered;

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay, dataType);
    }
}
