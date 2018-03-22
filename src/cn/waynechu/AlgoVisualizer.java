package cn.waynechu;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {

    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int radius, int number) {
        circles = new Circle[number];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            // 坐标为 r~w-r
            int x = random.nextInt(sceneWidth - 2 * radius + 1) + radius;
            int y = random.nextInt(sceneHeight - 2 * radius + 1) + radius;
            // 速度为-5~5（除去0，防止水平或纵向来回滚动）
            int randomX = random.nextInt(11) - 5;
            int randomY = random.nextInt(11) - 5;
            int vx = (randomX == 0 ? 1 : randomX);
            int vy = (randomY == 0 ? 1 : randomY);
            circles[i] = new Circle(x, y, radius, vx, vy);
        }


        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("myFrame", sceneWidth, sceneHeight);
            // 添加键盘监听事件
            frame.addKeyListener(new AlgoKeyListener());

            new Thread(this::run).start();
        });
    }

    /**
     * 执行动画逻辑
     */
    private void run() {
        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(10);

            // 更新数据
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
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

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int radius = 50;
        int number = 10;

        new AlgoVisualizer(sceneWidth, sceneHeight, radius, number);
    }
}
