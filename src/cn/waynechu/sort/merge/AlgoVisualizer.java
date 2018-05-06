package cn.waynechu.sort.merge;

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
    private MergeSortData data;
    private AlgoFrame frame;
    private int delay;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay) {
        this(sceneWidth, sceneHeight, n, delay, MergeSortData.Type.Random);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay, MergeSortData.Type dataType) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay must be large or equals 0  !");
        }
        // 初始化数据
        data = new MergeSortData(n, sceneHeight, dataType);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("归并排序可视化", sceneWidth, sceneHeight);
            // 开启新线程执行动画逻辑
            new Thread(this::run).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        setData(-1, -1, -1);
        mergeSort(0, data.getNumbers().length - 1);
        setData(0, data.getNumbers().length - 1, data.getNumbers().length - 1);
    }

    public void mergeSort(int left, int right) {
        if (left >= right) {
            return;
        }
        setData(left, right, -1);

        // 递归分解为左右都有序两部分
        int mid = left + (right - left) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);

        // 将 numbers[left, mid] 和 numbers[mid+1, right] 两部分进行归并
        if (data.getNumbers()[mid] > data.getNumbers()[mid + 1]) {
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        // 创建临时数组
        int[] tmp = Arrays.copyOfRange(data.getNumbers(), left, right + 1);

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // 左半部分元素已经全部处理完
                data.getNumbers()[k] = tmp[j - left];
                j++;
            } else if (j > right) {
                // 右半部分元素已经全部处理完
                data.getNumbers()[k] = tmp[i - left];
                i++;
            } else if (tmp[i - left] < tmp[j - left]) {
                // 左半部分所指元素 < 右半部分所指元素
                data.getNumbers()[k] = tmp[i - left];
                i++;
            } else {
                // 左半部分所指元素 >= 右半部分所指元素
                data.getNumbers()[k] = tmp[j - left];
                j++;
            }
            setData(left, right, k);
        }
    }

    /**
     * 修改状态量并重绘
     */
    private void setData(int left, int right, int mergeIndex) {
        data.left = left;
        data.right = right;
        data.mergeIndex = mergeIndex;
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
        MergeSortData.Type dataType = MergeSortData.Type.Random;

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay, dataType);
    }
}
