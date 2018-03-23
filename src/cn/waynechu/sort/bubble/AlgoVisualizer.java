package cn.waynechu.sort.bubble;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * 冒泡排序可视化
 *
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private BubbleSortData data;
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
        this.data = new BubbleSortData(n, sceneHeight);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame("冒泡排序可视化", sceneWidth, sceneHeight);
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
        setIndexData(data.getNumbers().length, 0, 1);

        // 更新数据
        for (int i = data.getNumbers().length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                setIndexData(i + 1, j, j + 1);
                if (data.getNumbers()[j + 1] > data.getNumbers()[j]) {
                    data.swap(j + 1, j);
                    setIndexData(i + 1, j + 1, j);
                } else {
                    setIndexData(i + 1, j, j + 1);
                }
            }
            // 输出每一轮排序后的结果
            setIndexData(i, -1, -1);
            System.out.println(Arrays.toString(data.getNumbers()));
        }
        setIndexData(0, -1, -1);
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int orderedIndex, int minCompareIndex, int maxCompareIndex) {
        data.orderedIndex = orderedIndex;
        data.minCompareIndex = minCompareIndex;
        data.maxCompareIndex = maxCompareIndex;
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
        int delay = 10;

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay);
    }
}
