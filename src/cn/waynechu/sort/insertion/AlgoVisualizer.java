package cn.waynechu.sort.insertion;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * 插入排序可视化
 *
 * @author waynechu
 * Created 2018-03-24 13:41
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private InsertionSortData data;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 画面停留时间
     **/
    private int delay;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay) {
        this(sceneWidth, sceneHeight, n, delay, InsertionSortData.Type.Random);
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, int delay, InsertionSortData.Type dataType) {
        if (delay < 0) {
            throw new IllegalArgumentException("delay must be large or equals 0  !");
        }
        // 初始化数据
        this.data = new InsertionSortData(n, sceneHeight, dataType);
        this.delay = delay;
        // 初始化视图
        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame("插入排序可视化", sceneWidth, sceneHeight);
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
        setIndexData(-1, 0);

        // 更新数据
        for (int i = 0; i < data.getNumbers().length; i++) {
            setIndexData(i, i);
            for (int j = i; j > 0 && data.getNumbers()[j] < data.getNumbers()[j - 1]; j--) {
                data.swap(j - 1, j);
                setIndexData(i + 1, j - 1);
            }
            System.out.println(Arrays.toString(data.getNumbers()));
        }
        setIndexData(data.getNumbers().length, -1);
    }

    /**
     * 修改状态量并重绘
     */
    private void setIndexData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
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
        // 初始化数据的状态
        InsertionSortData.Type dataType = InsertionSortData.Type.Random;

        new AlgoVisualizer(sceneWidth, sceneHeight, n, delay, dataType);
    }
}
