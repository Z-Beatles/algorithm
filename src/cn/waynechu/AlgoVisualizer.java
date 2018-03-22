package cn.waynechu;

import java.awt.*;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 13:47
 */
public class AlgoVisualizer {

    private Circle[] circles;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int number) {
        int r = 50;
        circles = new Circle[number];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            // 坐标为 r~w-r
            int x = random.nextInt(sceneWidth - 2 * r + 1) + r;
            int y = random.nextInt(sceneHeight - 2 * r + 1) + r;
            // 速度为-5~5（除去0，防止水平或纵向来回滚动）
            int randomX = random.nextInt(11) - 5;
            int randomY = random.nextInt(11) - 5;
            int vx = (randomX == 0 ? 1 : randomX);
            int vy = (randomY == 0 ? 1 : randomY);
            circles[i] = new Circle(x, y, r, vx, vy);
        }


        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("myFrame", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(10);

            // 更新数据
            for (Circle circle : circles) {
                circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
            }
        }
    }
}
