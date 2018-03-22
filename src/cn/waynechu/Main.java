package cn.waynechu;

import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int canvasWidth = 800;
        int canvasHeight = 800;

        new Random(3);
        int number = 10;
        int r = 50;
        Circle[] circles = new Circle[number];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            // 坐标为 r~w-r
            int x = random.nextInt(canvasWidth - 2 * r + 1) + r;
            int y = random.nextInt(canvasHeight - 2 * r + 1) + r;
            // 速度为-5~5
            int vx = random.nextInt(11) - 5;
            int vy = random.nextInt(11) - 5;
            circles[i] = new Circle(x, y, r, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("myFrame", canvasWidth, canvasHeight);

            new Thread(() -> {
                while (true) {
                    // 绘制数据
                    frame.render(circles);
                    AlgoVisHelper.pause(10);

                    // 更新数据
                    for (Circle circle : circles) {
                        circle.move(0, 0, canvasWidth, canvasHeight);
                    }
                }
            }).start();
        });
    }
}
