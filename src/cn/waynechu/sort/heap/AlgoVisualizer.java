package cn.waynechu.sort.heap;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * 堆排序可视化
 *
 * @author waynechu
 * Created 2018-04-01 11:31
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private HeapSortData data;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 画面停留时间
     **/
    private int delay;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay must be large or equals 0  !");
        }
        // 初始化数据
        this.data = new HeapSortData(n, sceneHeight);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame("堆排序可视化", sceneWidth, sceneHeight);
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
        int heapSize = data.getNumbers().length;
        setIndexData(heapSize);

        // 构建最大堆
        for (int i = (heapSize - 1) / 2; i >= 0; i--) {
            shiftDown(heapSize, i);
        }

        // 堆排序
        for (int i = heapSize - 1; i > 0; i--) {
            // 把当前堆中最大的元素放到它合适的位置 0->i
            data.swap(0, i);
            // 其余素从number[0]开始继续构建最大堆
            shiftDown(i, 0);
            setIndexData(i);
        }
        setIndexData(0);
    }

    private void shiftDown(int heapSize, int curr) {
        // 有子节点（左孩子）
        while (2 * curr + 1 < heapSize) {
            // 此轮循环中，numbers[curr]和arr[j]交换位置
            int j = 2 * curr + 1;
            // 右孩子大于左孩子
            if (j + 1 < heapSize && data.getNumbers()[j + 1] > data.getNumbers()[j]) {
                j += 1;
            }
            // 父节点大于两个子节点
            if (data.getNumbers()[curr] >= data.getNumbers()[j]) {
                break;
            }
            data.swap(curr, j);

            setIndexData(data.heapIndex);
            // 考察下一节点
            curr = j;
        }
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int heapIndex) {
        data.heapIndex = heapIndex;
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

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay);
    }
}
