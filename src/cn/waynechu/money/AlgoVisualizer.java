package cn.waynechu.money;

import cn.waynechu.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private int[] money;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 动画状态
     **/
    private boolean isAnimated = true;
    /**
     * 画面停留时间，单位ms
     **/
    private static final int DELAY = 60;
    /**
     * 每次重绘时的交易笔数
     **/
    private int iteration;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int number, int m, int iteration) {
        // 初始化数据
        money = new int[number];
        this.iteration = iteration;
        for (int i = 0; i < number; i++) {
            // 初始化钱数量为m
            money[i] = m;
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("模拟随机分钱问题", sceneWidth, sceneHeight);
            // 添加键盘监听事件
            frame.addKeyListener(new AlgoVisualizer.AlgoKeyListener());
            // 添加鼠标点击监听事件
            frame.addMouseListener(new AlgoVisualizer.AlgoMouseListener());
            // 开启新线程执行动画逻辑
            new Thread(this::run).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        while (true) {
            // 对财富值排序
            Arrays.sort(money);
            // 绘制数据
            frame.render(money);
            // 画面停留时间
            AlgoVisHelper.pause(DELAY);

            // 更新数据
            Random random = new Random();
            // 每次重绘交易笔数
            if (isAnimated) {
                for (int k = 0; k < iteration; k++) {
                    for (int i = 0; i < money.length; i++) {
                        //if (money[i] > 0) {
                        // 随机给另外一个人钱
                        int j = random.nextInt(money.length);
                        money[i] -= 1;
                        money[j] += 1;
                        //}
                    }
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // 如果按下空格键，则更改动画状态
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // 矫正画布点击位置，减去顶部按钮的高度
            e.translatePoint(-(frame.getBounds().width - frame.getCanvasWidth()), -(frame.getBounds().height - frame.getCanvasHeight()));
            System.out.println(e.getPoint());
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        // 人数
        int number = 100;
        // 初始财富值
        int money = 100;
        // 每次重绘时的交易笔数
        int iteration = 40;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(sceneWidth, sceneHeight, number, money, iteration);
    }
}
