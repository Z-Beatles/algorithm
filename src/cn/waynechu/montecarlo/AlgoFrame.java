package cn.waynechu.montecarlo;

import cn.waynechu.bubble.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @author waynechu
 * Created 2018-02-27 22:44
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private Circle circle;
    private LinkedList<Point> points;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        // 设置Content Pane内容面板
        this.setContentPane(canvas);
        // 窗口大小不可变
        setResizable(false);
        // 默认关闭操作
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口可见
        setVisible(true);
        // 调整frame大小以适合子组件的首选大小和布局
        pack();
    }

    public void render(Circle circle, LinkedList<Point> points) {
        this.circle = circle;
        this.points = points;
        // 重新绘制
        repaint();
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 启用双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 获得绘图上下文环境
            Graphics2D graphics2D = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(hints);

            // 设置画笔宽度
            AlgoVisHelper.setStrokeWidth(graphics2D, 3);
            // 设置颜色
            AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Blue);
            // 绘制图形
            AlgoVisHelper.strokeCircle(graphics2D, circle.getX(), circle.getY(), circle.getR());
            Point point;
            for (int i = 0; i < points.size(); i++) {
                point = points.get(i);
                if (circle.contain(point)) {
                    AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Red);
                } else {
                    AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Green);
                }
                AlgoVisHelper.fillCircle(graphics2D, point.x, point.y, 3);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
