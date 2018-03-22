package cn.waynechu.montecarlo;

import cn.waynechu.bubble.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private Circle circle;
    private LinkedList<Point> points;
    /**
     * 视图
     **/
    private AlgoFrame frame;
    /**
     * 打点的个数
     **/
    private int number;
    /**
     * 动画状态
     **/
    private boolean isAnimated = true;
    /**
     * 画面停留时间
     **/
    private static final int DELAY = 16;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int number) {
        if (sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("this demo must be run in a square.");
        }
        // 初始化数据
        this.number = number;
        circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        points = new LinkedList<>();

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("蒙特卡洛算法计算PI值", sceneWidth, sceneHeight);
            // 添加键盘监听事件
            frame.addKeyListener(new AlgoKeyListener());
            // 添加鼠标点击监听事件
            frame.addMouseListener(new AlgoMouseListener());
            // 开启新线程执行动画逻辑
            new Thread(this::run).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        for (int i = 0; i < number; i++) {
            // 绘制数据
            frame.render(circle, points);
            // 画面停留时间
            AlgoVisHelper.pause(DELAY);
            // 更新数据
            if (isAnimated) {
                Random random = new Random();
                int x = random.nextInt(frame.getCanvasWidth()) + 1;
                int y = random.nextInt(frame.getCanvasHeight()) + 1;
                Point p = new Point(x, y);
                points.add(p);
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
        // 打点个数
        int number = 10000;

        new AlgoVisualizer(sceneWidth, sceneHeight, number);
    }
}
