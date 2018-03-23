package cn.waynechu.sort.selection;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private SelectionSortData data;
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
        data = new SelectionSortData(n, sceneHeight);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("选择排序可视化", sceneWidth, sceneHeight);
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
        setIndexData(-1, -1, -1);

        // 更新数据
        for (int i = 0; i < data.getNumbers().length; i++) {
            // 寻找[i, n)里最小值的索引
            int minIndex = i;
            setIndexData(i, -1, minIndex);
            for (int j = i + 1; j < data.getNumbers().length; j++) {
                setIndexData(i, j, minIndex);
                if (data.getNumbers()[j] < data.getNumbers()[minIndex]) {
                    minIndex = j;
                    setIndexData(i, j, minIndex);
                }
            }
            // 交换数值
            data.swap(i, minIndex);
            // 输出每一轮排序后的结果
            setIndexData(i + 1, -1, -1);
            System.out.println(Arrays.toString(data.getNumbers()));
        }
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
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
