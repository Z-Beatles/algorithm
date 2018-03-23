package cn.waynechu.sort.selection;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
     * 动画状态
     **/
    private boolean isAnimated = true;
    /**
     * 画面停留时间
     **/
    private static final int DELAY = 16;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n) {
        // 初始化数据
        data = new SelectionSortData(n, sceneHeight);
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
        printNumbers(data.getMembers());
        // 绘制数据
        frame.render(data);
        // 画面停留时间
        AlgoVisHelper.pause(DELAY);

        // 更新数据
        for (int i = 0; i < data.getMembers().length; i++) {
            // 寻找[i, n)里最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < data.getMembers().length; j++) {
                // 设置正在
                data.getMembers()[j].setColor(AlgoVisHelper.Blue);
                frame.render(data);
                if (data.getMembers()[j].getNumber() < data.getMembers()[minIndex].getNumber()) {
                    minIndex = j;
                    data.getMembers()[j].setColor(AlgoVisHelper.Red);
                    frame.render(data);
                }
            }
            // 设置已排序的颜色为DeepOrange
            data.getMembers()[minIndex].setColor(AlgoVisHelper.DeepOrange);
            // 交换数组中的两个对象
            data.swap(i, minIndex);
            // 重新渲染
            frame.render(data);
            AlgoVisHelper.pause(DELAY);
            // 输出每一轮排序后的结果
            printNumbers(data.getMembers());
        }
    }

    /**
     * 输出排序结果
     **/
    private void printNumbers(Member[] members) {
        for (int i = 0; i < members.length; i++) {
            if (i == members.length - 1) {
                System.out.print(members[i].getNumber());
            } else {
                System.out.print(members[i].getNumber() + ",");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        // 排序数的个数
        int n = 100;

        new AlgoVisualizer(sceneWidth, sceneHeight, n);
    }
}
