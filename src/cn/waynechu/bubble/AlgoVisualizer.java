package cn.waynechu.bubble;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {
    /**
     * 数据
     **/
    private Circle[] circles;
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

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int radius, int number, int fromSpeed, int toSpeed) {
        // 初始化数据
        circles = new Circle[number];
        Random random = new Random();

        // 添加第一个小球
        // 坐标为 r~w-r
        int x1 = random.nextInt(sceneWidth - 2 * radius + 1) + radius;
        int y1 = random.nextInt(sceneHeight - 2 * radius + 1) + radius;
        // 速度为-5~5（除去0，防止水平或纵向来回滚动）
        int randomX1 = random.nextInt(toSpeed - fromSpeed + 1) + fromSpeed;
        int randomY1 = random.nextInt(toSpeed - fromSpeed + 1) + fromSpeed;
        int vx1 = (randomX1 == 0 ? 1 : randomX1);
        int vy1 = (randomY1 == 0 ? 1 : randomY1);
        circles[0] = new Circle(x1, y1, radius, vx1, vy1);

        // 添加剩余小球
        for (int i = 1; i < number; i++) {
            // 圆心坐标
            int x = random.nextInt(sceneWidth - 2 * radius + 1) + radius;
            int y = random.nextInt(sceneHeight - 2 * radius + 1) + radius;
            // 速度
            int randomX = random.nextInt(toSpeed - fromSpeed + 1) + fromSpeed;
            int randomY = random.nextInt(toSpeed - fromSpeed + 1) + fromSpeed;
            int vx = (randomX == 0 ? 1 : randomX);
            int vy = (randomY == 0 ? 1 : randomY);
            // 判断要添加的小球是否和已存在的小球重叠
            for (int j = 0; j < i; j++) {
                int dx = x - circles[j].x;
                int dy = y - circles[j].y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                int l = radius + circles[j].getR();
                if (distance > l) {
                    // 未重叠则添加该小球
                    circles[i] = new Circle(x, y, radius, vx, vy);
                } else {
                    // 重新添加
                    i -= 1;
                    break;
                }
            }
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("模拟小球碰撞", sceneWidth, sceneHeight);
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
        while (true) {
            // 绘制数据
            frame.render(circles);
            // 画面停留时间
            AlgoVisHelper.pause(DELAY);

            // 更新数据
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
                checkCollisionOthers(circles);
            }
        }
    }

    /**
     * 检测是否和其他小球碰撞
     **/
    private void checkCollisionOthers(Circle[] circles) {
        for (int i = 0; i < circles.length - 1; i++) {
            for (int j = i + 1; j < circles.length; j++) {
                int x = circles[i].x - circles[j].x;
                int y = circles[i].y - circles[j].y;
                double distance = Math.sqrt(x * x + y * y);
                int l = circles[i].getR() + circles[j].getR();
                if (distance <= l) {
                    // 碰撞则交换速度
                    int tmpX = circles[i].vx;
                    circles[i].vx = circles[j].vx;
                    circles[j].vx = tmpX;

                    int tmpY = circles[i].vy;
                    circles[i].vy = circles[j].vy;
                    circles[j].vy = tmpY;
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
        int radius = 40;
        int number = 10;
        int fromSpeed = -5;
        int toSpeed = 5;

        new AlgoVisualizer(sceneWidth, sceneHeight, radius, number, fromSpeed, toSpeed);
    }
}
