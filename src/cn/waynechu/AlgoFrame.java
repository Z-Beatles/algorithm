package cn.waynechu;

import javax.swing.*;
import java.awt.*;

/**
 * @author waynechu
 * Created 2018-02-27 22:44
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        // 设置Content Pane内容面板
        this.setContentPane(canvas);
        pack();

        // 窗口大小不可变
        setResizable(false);
        // 默认关闭操作
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口可见
        setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
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
            AlgoVisHelper.setStrokeWidth(graphics2D, 5);
            // 设置颜色
            AlgoVisHelper.setColor(graphics2D, Color.BLUE);
            // 绘制图形
            AlgoVisHelper.fillCircle(graphics2D, 250, 250, 200);

            AlgoVisHelper.setColor(graphics2D, Color.RED);
            AlgoVisHelper.strokeCircle(graphics2D, 250, 250, 200);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
